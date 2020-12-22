package com.hived.pojo;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "hivd")
public class ProductPostPojo {

	private String PK;
	private String SK;
	private String entityType;
	private String reviewId;
	private String title;
	private String sub_title;
	private String rating;
	private String profile_url;
	private String username;
	private List<String> images;
	private String userId;
	private String creationDate;
	private String paginationValue1;
	private String paginationValue2;

	public String getPK() {
		return PK;
	}

	public void setPk(String PK) {
		this.PK = PK;
	}

	@DynamoDBRangeKey(attributeName = "SK")
	public String getSK() {
		return SK;
	}

	public void setSK(String SK) {
		this.SK = SK;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@DynamoDBHashKey(attributeName = "PK")
	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getPaginationValue1() {
		return paginationValue1;
	}

	public void setPaginationValue1(String paginationValue1) {
		this.paginationValue1 = paginationValue1;
	}

	public String getPaginationValue2() {
		return paginationValue2;
	}

	public void setPaginationValue2(String paginationValue2) {
		this.paginationValue2 = paginationValue2;
	}
}
