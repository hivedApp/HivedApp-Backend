package com.hived.handler;

import org.apache.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.hived.request.Request;
import com.hived.response.Response;

public class CreateProductPostHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		Response response = null;
		try {
			int success = 1;
			if (success == 1) {
				response = new Response();
				return response;
			} else {
				response = new Response();
				return response;
			}
		} catch (Exception e) {
			logger.error("Save Product_Post details Failed", e);
			response = new Response();
			return response;
		}
	}

}