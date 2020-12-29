package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.ProductPojo;
import com.hived.request.Request;
import com.hived.response.ProductResponse;
import com.hived.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetProductHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		try {
			String userId = request.getId();
			List<ProductPojo> list = new ProductService().getProductByUserId(userId);
			List<ProductResponse> response = new ArrayList<ProductResponse>();
			if (list.size() > 0) {
				for (ProductPojo product : list) {
					ProductResponse productResponse = new ProductResponse();
					productResponse.setUsrerId(product.getUserId());
					productResponse.setProductId(product.getProductId());
					productResponse.setDatetime(product.getCreationDate());
					productResponse.setProduct_description(product.getProduct_description());
					productResponse.setUser_profile_url("https://my-profile-image.s3.ap-south-1.amazonaws.com/facebooks.jpg");
					productResponse.setUserName(product.getUserName());
					String image = product.getAddProductPhotos().get(0);
					productResponse.setImages(image);
					response.add(productResponse);
				}
				return response;
			} else {
				return "Data not found";
			}
		} catch (Exception ex) {
			logger.error("Error in retrieving product details: " + ex);
			return "Exception " + ex;
		}

	}

//	public static void main(String[] args) {
//		try {
//			List<ProductPojo> list = new ProductService().getProductByUserId("12");
//			List<ProductResponse> response = new ArrayList<ProductResponse>();
//			ObjectMapper mapper = new ObjectMapper();
//			if (list.size() > 0) {
//				for (ProductPojo p : list) {
//					ProductResponse productResponse = new ProductResponse();
//					productResponse.setUsrerId(p.getUserId());
//					productResponse.setProductId(p.getProductId());
//					productResponse.setDatetime(p.getCreationDate());
//					productResponse.setProduct_description(p.getProduct_description());
//					productResponse.setUser_profile_url("null");
//					productResponse.setUserName(p.getUserName());
//					String image = p.getAddProductPhotos().get(0);
//					productResponse.setImages(image);
//					response.add(productResponse);
//				}
//				String jsonString = mapper.writeValueAsString(response);
//				System.out.println(jsonString);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
