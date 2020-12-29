package com.hived.response;

public class ProductPostResponse {

	private String usrerId;
	private String postId;
	private String datetime;
	private String product_description;
	private String user_profile_url;
	private String userName;
	private String images;

	public String getUsrerId() {
		return usrerId;
	}

	public void setUsrerId(String usrerId) {
		this.usrerId = usrerId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPosttId(String postId) {
		this.postId = postId;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getUser_profile_url() {
		return user_profile_url;
	}

	public void setUser_profile_url(String user_profile_url) {
		this.user_profile_url = user_profile_url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}
