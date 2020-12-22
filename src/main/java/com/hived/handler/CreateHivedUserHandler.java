package com.hived.handler;

import org.apache.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.HivedUserPojo;
import com.hived.request.Request;
import com.hived.response.Response;
import com.hived.service.HivedUserService;

public class CreateHivedUserHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		
		HivedUserPojo hivedUserPojo = null;
		Response response = null;
		try {
			hivedUserPojo = request.getHivedUserPojo();
			int success = new HivedUserService().saveHivedUser(hivedUserPojo);
			if (success == 1) {
				response = new Response();
				return response;
			} else {
				response = new Response();
				return response;
			}
		} catch (Exception e) {
			logger.error("Save Hived User Failed", e);
			response = new Response();
			return response;
		}
	}

}