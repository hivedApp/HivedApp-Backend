package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.Images;
import com.hived.pojo.ProductPostPojo;
import com.hived.request.Request;
import com.hived.response.ProductPostReviewResponse;
import com.hived.response.Response;
import com.hived.service.ProductPostService;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetProductPostHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		try {
			ProductPostPojo productPostPojo = request.getProductPostPojo();
			String uId = productPostPojo.getUserId().toString();
			String pValue1 = productPostPojo.getPaginationValue1();
			String pValue2 = productPostPojo.getPaginationValue2();
			List<ProductPostPojo> listReview = new ProductPostService().getReviewListByUserId(uId, pValue1, pValue2);
			List<ProductPostReviewResponse> ReviewResponseList = new ArrayList<ProductPostReviewResponse>();
			Response reviewResponse = new Response();
			// ObjectMapper mapper = new ObjectMapper();
			 String pval1=null;
			 String pval2=null;
			if (listReview.size() > 0) {
				// ObjectMapper mapper = new ObjectMapper();
				for (ProductPostPojo review : listReview) {
					List<Images> images = new ArrayList<Images>();
					ProductPostReviewResponse hivedUserResponse = new ProductPostReviewResponse();
					for (String img : review.getImages()) {
						Images image = new Images();
						image.setImage_url(img);
						image.setType("image");
						images.add(image);
					}
					hivedUserResponse.setId(review.getReviewId().toString());
					hivedUserResponse.setUserId(review.getUserId().toString());
					hivedUserResponse.setDatetime(review.getCreationDate());
					hivedUserResponse.setTitle(review.getTitle());
					hivedUserResponse.setSub_title(review.getSub_title());
					hivedUserResponse.setRating(review.getRating());
					hivedUserResponse.setProfile_url(review.getProfile_url());
					hivedUserResponse.setUserName(review.getUsername());
					hivedUserResponse.setImages(images);
					pval1=review.getPaginationValue1();
					pval2=review.getPaginationValue2();
					ReviewResponseList.add(hivedUserResponse);
				}
				reviewResponse.setProductPostReviewResponse(ReviewResponseList);
				reviewResponse.setPaginationKeyValue1(pval1);
				reviewResponse.setPaginationKeyValue2(pval2);
				// String jsonString = mapper.writeValueAsString(reviewResponse);
				// System.out.println(jsonString);
				return reviewResponse;
			}else {
				return "Not data found";
			}
		} catch (Exception ex) {
			logger.error("Error in retrieving hived review user details: " + ex);
			return "Exception " + ex;
		}
	}
}
