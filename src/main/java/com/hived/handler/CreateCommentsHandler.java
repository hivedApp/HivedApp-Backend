package com.hived.handler;

import org.apache.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.CommentsPojo;
import com.hived.request.Request;
import com.hived.response.Response;
import com.hived.service.CommentsService;

public class CreateCommentsHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		CommentsPojo commentsPojo = null;
		Response response = null;
		try {
			commentsPojo = request.getCommentsPojo();
			int success = new CommentsService().saveComments(commentsPojo);
			if (success == 1) {
				response = new Response();
				return response;
			} else {
				response = new Response();
				return response;
			}
		} catch (Exception e) {
			logger.error("Save Comments details Failed", e);
			response = new Response();
			return response;
		}
	}

}