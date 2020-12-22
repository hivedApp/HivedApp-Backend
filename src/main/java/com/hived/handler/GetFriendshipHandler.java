package com.hived.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.hived.pojo.FriendshipPojo;
import com.hived.request.Request;
import com.hived.service.FriendshipService;

import org.apache.log4j.Logger;

public class GetFriendshipHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	public Object handleRequest(Request request, Context context) {
		
		try {
			String friendShipId = request.getId();
			FriendshipPojo friendshipPojo = new FriendshipService().getFreindshipDetailsById(friendShipId);
			return friendshipPojo;
		} catch (Exception ex) {
			logger.error("Error in retrieving friendship details: " + ex);
			return "Exception " + ex;
		}
	}
}
