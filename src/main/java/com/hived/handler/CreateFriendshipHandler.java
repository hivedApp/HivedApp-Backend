package com.hived.handler;

import org.apache.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.FriendshipPojo;
import com.hived.request.Request;
import com.hived.response.Response;
import com.hived.service.FriendshipService;

public class CreateFriendshipHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		FriendshipPojo friendshipPojo = null;
		Response response = null;
		try {
			friendshipPojo = request.getFriendshipPojo();
			int success = new FriendshipService().saveFriendship(friendshipPojo);
			if (success == 1) {
				response = new Response();
				return response;
			} else {
				response = new Response();
				return response;
			}
		} catch (Exception e) {
			logger.error("Save Friendship details Failed", e);
			response = new Response();
			return response;
		}
	}

}