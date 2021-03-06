package com.hived.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.hived.pojo.ProductPojo;

public class ProductService {

	private final Logger logger = Logger.getLogger(this.getClass());

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "hiveds";

	public String saveProduct(ProductPojo productPojo) {
		Table table = dynamoDB.getTable(tableName);
		String success = null;
		try {
			String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
			String dateString = simpleDateFormat.format(new Date());
			UUID uuid = UUID.randomUUID();
			Item item = new Item().withPrimaryKey("PK", uuid.toString())
					.withString("SK", productPojo.getUserId().toString()).withString("entityType", "product")
					.withString("userName", productPojo.getUserName())
					.withString("product_select", productPojo.getProduct_select())
					.withString("product_brand", productPojo.getProduct_brand())
					.withString("product_model", productPojo.getProduct_model())
					.withList("add_product_Photos", productPojo.getAddProductPhotos())
					.withString("add_product_comment", productPojo.getAdd_product_comment())
					.withString("product_title", productPojo.getProduct_title())
					.withString("product_description", productPojo.getProduct_description())
					.withInt("product_rating", productPojo.getProduct_rating())
					.withString("product_seller", productPojo.getProduct_seller())
					.withString("product_branch", productPojo.getProduct_branch())
					.withString("product_sales_person", productPojo.getProduct_sales_person())
					.withString("product_saller_title", productPojo.getProduct_saller_title())
					.withString("product_seller_description", productPojo.getProduct_seller_description())
					.withInt("product_seller_rating", productPojo.getProduct_seller_rating())
					.withString("CreationDate", dateString);
			table.putItem(item);
			success = uuid.toString();
		} catch (Exception e) {
			success = null;
			System.err.println("Create items failed.");
			System.err.println(e.getMessage());
		}
		logger.info("ProductService.saveProduct()->save product details!");
		return success;
	}

	public String updateProductReview(ProductPojo productPojo) {
		Table table = dynamoDB.getTable(tableName);
		String success = null;
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("PK", productPojo.getProductId(), "SK", productPojo.getUserId().toString())
				.withUpdateExpression(
						"set product_title = :ptitle, product_description=:pdesc, product_rating=:prating")
				.withValueMap(new ValueMap().withString(":ptitle", productPojo.getProduct_title())
						.withString(":pdesc", productPojo.getProduct_description())
						.withInt(":prating", productPojo.getProduct_rating()))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			success = productPojo.getProductId();
			logger.info(outcome);
		} catch (Exception e) {
			System.err.println("Unable to update item: " + productPojo.getProductId());
			System.err.println(e.getMessage());
			success = null;
			logger.error("ProductService.updateProductReview->" + e);
		}
		logger.info("ProductService.updateProductReview->");
		return success;
	}

	public String updateProductSellerReview(ProductPojo productPojo) {
		Table table = dynamoDB.getTable(tableName);
		String success = null;
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("PK", productPojo.getProductId(), "SK", productPojo.getUserId().toString())
				.withUpdateExpression(
						"set product_seller = :pstitle, product_branch=:psbranch, product_sales_person=:psperson, product_saller_title=:pstitle, product_seller_rating=:psrating, product_seller_description=:psdesc")
				.withValueMap(new ValueMap().withString(":pstitle", productPojo.getProduct_seller())
						.withString(":psbranch", productPojo.getProduct_branch())
						.withString(":psperson", productPojo.getProduct_sales_person())
						.withString(":pstitle", productPojo.getProduct_saller_title())
						.withInt(":psrating", productPojo.getProduct_seller_rating())
						.withString(":psdesc", productPojo.getProduct_seller_description()))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			System.out.println("Updating the item...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			success = productPojo.getProductId();
			logger.info(outcome);

		} catch (Exception e) {
			System.err.println("Unable to update item: " + productPojo.getProductId());
			System.err.println(e.getMessage());
			success = null;
			logger.error("ProductService.updateProductReview->" + e);
		}
		logger.info("ProductService.updateProductReview->");
		return success;
	}
}
