package ca.homedepot.customerreview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "customer_reviews")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerReviewModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String headline;
	private String comment;
	private Double rating;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductModel product;

	@ManyToOne
	private LanguageModel language;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(final String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(final Double rating) {
		this.rating = rating;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public LanguageModel getLanguage()
	{
		return language;
	}

	public void setLanguage(LanguageModel language)
	{
		this.language = language;
	}
}
