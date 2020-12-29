package com.hived.response;

import java.util.List;

public class Response {

	private String paginationKeyValue1;
	private String paginationKeyValue2;
	private List<ProductPostReviewResponse> productPostReviewResponse;
	private String product_seller;
	private String error;
	private String productId;
	private List<String> preSignedURL;

	public String getPaginationKeyValue1() {
		return paginationKeyValue1;
	}

	public void setPaginationKeyValue1(String paginationKeyValue1) {
		this.paginationKeyValue1 = paginationKeyValue1;
	}

	public String getPaginationKeyValue2() {
		return paginationKeyValue2;
	}

	public void setPaginationKeyValue2(String paginationKeyValue2) {
		this.paginationKeyValue2 = paginationKeyValue2;
	}

	public List<ProductPostReviewResponse> getProductPostReviewResponse() {
		return productPostReviewResponse;
	}

	public void setProductPostReviewResponse(List<ProductPostReviewResponse> productPostReviewResponse) {
		this.productPostReviewResponse = productPostReviewResponse;
	}

	public String getProduct_seller() {
		return product_seller;
	}

	public void setProduct_seller(String product_seller) {
		this.product_seller = product_seller;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<String> getPreSignedURL() {
		return preSignedURL;
	}

	public void setPreSignedURL(List<String> preSignedURL) {
		this.preSignedURL = preSignedURL;
	}

}
