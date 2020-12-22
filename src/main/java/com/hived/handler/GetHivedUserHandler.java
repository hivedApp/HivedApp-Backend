package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.HivedUserPojo;
import com.hived.request.Request;
import com.hived.service.HivedUserService;
import org.apache.log4j.Logger;

public class GetHivedUserHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		try {
			String userId = request.getId();
			HivedUserPojo hivedUserPojo = new HivedUserService().getHivedUserByUserId(userId);
		    return hivedUserPojo;
		} catch (Exception ex) {
			logger.error("Error in retrieving hived user: " + ex);
			return "Exception " + ex;
		}
	}
//	public static void main(String[] args) {
//		HivedUserPojo hivedUserPojo = new HivedUserService().getHivedUserByUserId("857e84a5-0c09-4ff7-82a7-c34215c0f785");
//		System.out.println(hivedUserPojo);
//	}
}
