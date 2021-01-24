package ca.homedepot.customerreview.dao;

import java.util.List;

import ca.homedepot.customerreview.model.LanguageModel;
import ca.homedepot.customerreview.model.ProductModel;
import ca.homedepot.customerreview.model.CustomerReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface CustomerReviewDao extends JpaRepository<CustomerReviewModel, Long>
{
	@Query("select reviews from CustomerReviewModel reviews where reviews.product = ?1")
	List<CustomerReviewModel> getAllReviews(ProductModel product);

	@Query("select count(reviews) from CustomerReviewModel reviews where reviews.product = ?1")
	Integer getNumberOfReviews(ProductModel product);

	@Query("select avg(reviews.rating) from CustomerReviewModel reviews where reviews.product = ?1")
	Double getAverageRating(ProductModel product);

	@Query("select reviews from CustomerReviewModel reviews where reviews.product = ?1 and reviews.language = ?2")
	List<CustomerReviewModel> getReviewsForProductAndLanguage(ProductModel product, LanguageModel language);

	@Transactional
	@Modifying
	@Query("delete from CustomerReviewModel reviews where reviews.id = ?1")
	void deleteReview(Long id);
}
