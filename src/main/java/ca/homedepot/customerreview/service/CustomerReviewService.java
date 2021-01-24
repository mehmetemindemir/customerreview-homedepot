package ca.homedepot.customerreview.service;

import java.util.List;

import ca.homedepot.customerreview.model.LanguageModel;
import ca.homedepot.customerreview.model.ProductModel;
import ca.homedepot.customerreview.model.UserModel;
import ca.homedepot.customerreview.model.CustomerReviewModel;


public interface CustomerReviewService
{
	CustomerReviewModel createCustomerReview(Double rating, String headline, String comment, ProductModel product, UserModel user);

	void updateCustomerReview(CustomerReviewModel customer, UserModel user, ProductModel product);

	List<CustomerReviewModel> getReviewsForProduct(ProductModel product);

	Double getAverageRating(ProductModel product);

	Integer getNumberOfReviews(ProductModel product);

	List<CustomerReviewModel> getReviewsForProductAndLanguage(ProductModel product, LanguageModel language);

	void deleteCustomerReview(Long id);
}
