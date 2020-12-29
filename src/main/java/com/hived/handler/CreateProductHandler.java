package com.hived.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.Images;
import com.hived.pojo.ProductPojo;
import com.hived.presignedURL.GeneratePresignedURL;
import com.hived.request.Request;
import com.hived.response.Response;
import com.hived.service.ProductService;

public class CreateProductHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {

		ProductPojo productPojo = null;
		Response response = null;
		try {
			productPojo = request.getProductPojo();
			if (productPojo.getProductId().equals("") || productPojo.getProductId() == null) {
				List<String> preURL = new ArrayList<String>();
				List<String> imageUrlList = new ArrayList<String>();
				if (productPojo.getAdd_product_Photos().size() > 0) {
					for (Images img : productPojo.getAdd_product_Photos()) {
						// imageList.add(img.getImage_url());
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");
						LocalDateTime now = LocalDateTime.now();
						String date = dtf.format(now);
						String presignedURL = new GeneratePresignedURL()
								.getPresignedURL(productPojo.getUserId()+"-"+date+ img.getType());
						preURL.add(presignedURL);
						String url = "https://my-profile-image.s3.ap-south-1.amazonaws.com/" + productPojo.getUserId()+"-"+date+ img.getType();
						imageUrlList.add(url);
					}
				}
				productPojo.setAddProductPhotos(imageUrlList);
				String success = new ProductService().saveProduct(productPojo);
				if (success != null) {
					response = new Response();
					response.setProductId(success);
					response.setPreSignedURL(preURL);
					response.setProduct_seller("Successfully add product review");
					logger.info(success + " -----Successfully add product review-----");
					return response;
				} else {
					response = new Response();
					response.setError("Something went wrong. Please try again later.");
					logger.info(success + " -----Something went wrong. Please try again later -----");
					return response;
				}
			} else {
				if (productPojo.getProduct_title().equals("") || productPojo.getProduct_title() == null) {
					String update = new ProductService().updateProductSellerReview(productPojo);
					if (update != null) {
						response = new Response();
						response.setProductId(update);
						response.setProduct_seller("Successfully post review");
						logger.info(update + " -----Successfully post product review-----");

						return response;
					} else {
						response = new Response();
						response.setProduct_seller("Unable to update post review");
						return response;
					}
				} else {
					String update = new ProductService().updateProductReview(productPojo);
					if (update != null) {
						response = new Response();
						response.setProductId(update);
						response.setProduct_seller("Successfully updated page 2");
						logger.info(update + " -----Successfully update product review-----");
						return response;
					} else {
						response = new Response();
						response.setProduct_seller("Unable to update update page2");
						return response;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Save Product review details Failed", e);
			return e.getMessage();
		}
	}
}