package ca.homedepot.customerreview.forms;

import java.io.Serializable;


/**
 * @author Weichen Zhou
 */
public class CustomerReviewForm implements Serializable
{
	private Double rating;
	private String headline;
	private String comment;

	public Double getRating()
	{
		return rating;
	}

	public void setRating(Double rating)
	{
		this.rating = rating;
	}

	public String getHeadline()
	{
		return headline;
	}

	public void setHeadline(String headline)
	{
		this.headline = headline;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
