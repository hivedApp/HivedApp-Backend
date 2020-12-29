package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hived.pojo.ProductPostPojo;
import com.hived.request.Request;
import com.hived.response.ProductPostResponse;
import com.hived.service.ProductPostService;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetProductPostByUserIdHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		try {
			String userId=request.getId();
			List<ProductPostPojo> list = new ProductPostService().getProductPostByUserId(userId);
			List<ProductPostResponse> response = new ArrayList<ProductPostResponse>();
			if (list.size() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				for (ProductPostPojo product : list) {
					ProductPostResponse productResponse = new ProductPostResponse();
					productResponse.setUsrerId(product.getUserId());
					productResponse.setPosttId(product.getReviewId());
					productResponse.setDatetime(product.getCreationDate());
					productResponse.setProduct_description(product.getSub_title());
					productResponse.setUser_profile_url(product.getProfile_url());
					productResponse.setUserName(product.getUserName());
					String image = product.getImages().get(0);
					productResponse.setImages(image);
					response.add(productResponse);
				}
				String jsonString = mapper.writeValueAsString(response);
				System.out.println(jsonString);
				return response;
			} else {
				return "Product post data not found";
			}
		} catch (Exception ex) {
			logger.error("Error in retrieving product post details: " + ex);
			return "Exception " + ex;
		}
	}
}
