package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.hived.request.Request;
import org.apache.log4j.Logger;

public class GetCommentsHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		try {
			String productId = request.getId();
			return productId;
		} catch (Exception ex) {
			logger.error("Error in retrieving friendship details: " + ex);
			return "Exception " + ex;
		}
	}
}
