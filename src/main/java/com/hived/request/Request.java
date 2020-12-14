package com.hived.request;

import com.hived.pojo.CommentsPojo;
import com.hived.pojo.FriendshipPojo;
import com.hived.pojo.HivedUserPojo;
import com.hived.pojo.ProductPojo;
import com.hived.pojo.ProductPostPojo;

public class Request {

	private String httpMethod;
	private String id;
	private HivedUserPojo hivedUserPojo;
	private FriendshipPojo friendshipPojo;
	private ProductPojo productPojo;
	private ProductPostPojo productPostPojo;
	private CommentsPojo commentsPojo;

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HivedUserPojo getHivedUserPojo() {
		return hivedUserPojo;
	}

	public void setHivedUserPojo(HivedUserPojo hivedUserPojo) {
		this.hivedUserPojo = hivedUserPojo;
	}

	public FriendshipPojo getFriendshipPojo() {
		return friendshipPojo;
	}

	public void setFriendshipPojo(FriendshipPojo friendshipPojo) {
		this.friendshipPojo = friendshipPojo;
	}

	public ProductPojo getProductPojo() {
		if(productPojo == null) {
			productPojo = new ProductPojo();
		}
		return productPojo;
	}

	public void setProductPojo(ProductPojo productPojo) {
		this.productPojo = productPojo;
	}

	public ProductPostPojo getProductPostPojo() {
		if (productPostPojo == null) {
			productPostPojo = new ProductPostPojo();
		}
		return productPostPojo;
	}

	public void setProductPostPojo(ProductPostPojo productPostPojo) {
		this.productPostPojo = productPostPojo;
	}

	public CommentsPojo getCommentsPojo() {
		return commentsPojo;
	}

	public void setCommentsPojo(CommentsPojo commentsPojo) {
		this.commentsPojo = commentsPojo;
	}
}
