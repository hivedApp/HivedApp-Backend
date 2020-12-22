package com.hived.response;

import java.util.List;

import com.hived.pojo.Images;

public class ProductPostReviewResponse {

	private String id;
	private String userId;
	private String datetime;
	private String title;
	private String sub_title;
	private String rating;
	private String profile_url;
	private String userName;
	private List<Images> images;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
	
}
