package ca.homedepot.customerreview.controller;

import ca.homedepot.customerreview.dao.ProductDao;
import ca.homedepot.customerreview.dao.UserDao;
import ca.homedepot.customerreview.exception.ProductNotFoundException;
import ca.homedepot.customerreview.exception.UnprocessableEntity;
import ca.homedepot.customerreview.exception.UserNotFoundException;
import ca.homedepot.customerreview.forms.CustomerReviewForm;
import ca.homedepot.customerreview.model.CustomerReviewModel;
import ca.homedepot.customerreview.model.ProductModel;
import ca.homedepot.customerreview.model.UserModel;
import ca.homedepot.customerreview.service.CustomerReviewService;
import ca.homedepot.customerreview.util.ServicesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CustomerReviewController
{
	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomerReviewService customerReviewService;

	@GetMapping({ "products/{productId:\\d+}/reviews" })
	public List<CustomerReviewModel> getReviews(@PathVariable final Long productId,
			@RequestParam(required = false) final Double ratingFrom, @RequestParam(required = false) final Double ratingTo)
	{

		final ProductModel product = productDao.findOne(productId);
        Double from=(null!=ratingFrom?ratingFrom:null);
        Double to=(null!=ratingTo?ratingTo:null);
		if (product == null)
		{
			throw new ProductNotFoundException(productId);
		}
		if(null!=from && null!=to && from.doubleValue()>to.doubleValue()){
			from=ratingTo.doubleValue();
			to=ratingFrom.doubleValue();
		}
        List<CustomerReviewModel> reviewsRange=new ArrayList<>();
		List<CustomerReviewModel> reviews= customerReviewService.getReviewsForProduct(product);

		for (CustomerReviewModel review:reviews) {
			boolean cond1=true,cond2=true;
            if(null!=from && review.getRating().doubleValue()<from.doubleValue()){
               cond1=false;
            }
            if(null!=to && review.getRating().doubleValue()>to.doubleValue()){
               cond2=false;
            }
            if(cond1 && cond2){
                reviewsRange.add(review);
            }
        }
        return reviewsRange;
	}

	@PostMapping({ "products/{productId:\\d+}/users/{userId:\\d+}/reviews" })
	public CustomerReviewModel createReview(@PathVariable final Long userId, @PathVariable final Long productId,
			@RequestBody final CustomerReviewForm customerReviewForm)
	{
		final ProductModel product = productDao.findOne(productId);
		if (product == null)
		{
			throw new ProductNotFoundException(productId);
		}

		final UserModel user = userDao.findOne(userId);
		if (user == null)
		{
			throw new UserNotFoundException(userId);
		}
		if(null==customerReviewForm.getRating() || customerReviewForm.getRating()<0){
			throw  new UnprocessableEntity("Rating can not be negative! Rating:"+(null!=customerReviewForm.getRating()?customerReviewForm.getRating().doubleValue(): "null"));
		}
		if(ServicesUtil.isCurseWord(customerReviewForm.getComment())){
			throw new UnprocessableEntity("You can not use curse words in your comment");
		}
		if(ServicesUtil.isCurseWord(customerReviewForm.getHeadline())){
			throw new UnprocessableEntity("You can not use curse words in your headline");
		}
		return customerReviewService
				.createCustomerReview(customerReviewForm.getRating(), customerReviewForm.getHeadline(),
						customerReviewForm.getComment(), product, user);
	}

	@PostMapping({ "products" })
	public ProductModel createProduct()
	{
		final ProductModel product = new ProductModel();
		productDao.save(product);
		return product;
	}

	@PostMapping({ "users" })
	public UserModel createUser()
	{
		final UserModel user = new UserModel();
		userDao.save(user);
		return user;
	}
	@PostMapping({ "curse-word" })
	public String addCurseWord(@RequestParam final String curseWord)
	{

		ServicesUtil.addCurseWord(curseWord);
		return "Added curse word";
	}
	@DeleteMapping({ "reviews/{reviewId:\\d+}" })
	public void deleteReview(@PathVariable final Long reviewId)
	{
		customerReviewService.deleteCustomerReview(reviewId);
	}
}
