package com.hived.pojo;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "hiveds")
public class ProductPojo {

	private String PK;
	private String SK;
	private String entityType;
	private String productId;
	private Integer userId;
	private String userName;
	private String product_select;
	private String product_brand;
	private String product_model;
	private List<Images> add_product_Photos;
	private String add_product_comment;
	private String product_title;
	private String product_description;
	private Integer product_rating;
	private String product_seller;
	private String product_branch;
	private String product_sales_person;
	private String product_saller_title;
	private String product_seller_description;
	private Integer product_seller_rating;
	private String CreationDate;
	private List<String> addProductPhotos;

	@DynamoDBHashKey(attributeName = "PK")
	@DynamoDBAutoGeneratedKey
	public String getPK() {
		return PK;
	}

	public void setPK(String pK) {
		PK = pK;
	}

	@DynamoDBRangeKey(attributeName = "SK")
	public String getSK() {
		return SK;
	}

	public void setSK(String sK) {
		SK = sK;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProduct_select() {
		return product_select;
	}

	public void setProduct_select(String product_select) {
		this.product_select = product_select;
	}

	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getProduct_model() {
		return product_model;
	}

	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}

	public List<Images> getAdd_product_Photos() {
		return add_product_Photos;
	}

	public void setAdd_product_Photos(List<Images> add_product_Photos) {
		this.add_product_Photos = add_product_Photos;
	}

	public String getAdd_product_comment() {
		return add_product_comment;
	}

	public void setAdd_product_comment(String add_product_comment) {
		this.add_product_comment = add_product_comment;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public Integer getProduct_rating() {
		return product_rating;
	}

	public void setProduct_rating(Integer product_rating) {
		this.product_rating = product_rating;
	}

	public String getProduct_seller() {
		return product_seller;
	}

	public void setProduct_seller(String product_seller) {
		this.product_seller = product_seller;
	}

	public String getProduct_branch() {
		return product_branch;
	}

	public void setProduct_branch(String product_branch) {
		this.product_branch = product_branch;
	}

	public String getProduct_sales_person() {
		return product_sales_person;
	}

	public void setProduct_sales_person(String product_sales_person) {
		this.product_sales_person = product_sales_person;
	}

	public String getProduct_saller_title() {
		return product_saller_title;
	}

	public void setProduct_saller_title(String product_saller_title) {
		this.product_saller_title = product_saller_title;
	}

	public String getProduct_seller_description() {
		return product_seller_description;
	}

	public void setProduct_seller_description(String product_seller_description) {
		this.product_seller_description = product_seller_description;
	}

	public Integer getProduct_seller_rating() {
		return product_seller_rating;
	}

	public void setProduct_seller_rating(Integer product_seller_rating) {
		this.product_seller_rating = product_seller_rating;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public List<String> getAddProductPhotos() {
		return addProductPhotos;
	}

	public void setAddProductPhotos(List<String> addProductPhotos) {
		this.addProductPhotos = addProductPhotos;
	}

}
