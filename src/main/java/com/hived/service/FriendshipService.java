package com.hived.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.hived.pojo.FriendshipPojo;

public class FriendshipService {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "hivd";

	public int saveFriendship(FriendshipPojo friendshipPojo) {
		Table table = dynamoDB.getTable(tableName);
		int success = 0;
		try {
			Item item = new Item().withPrimaryKey("PK", friendshipPojo.getFriendshipId())
					.withString("SK", friendshipPojo.getFollowedUser())
					.withString("entityType", friendshipPojo.getEntityType())
					.withString("followingUser", friendshipPojo.getFollowingUser())
					.withString("followedUser", friendshipPojo.getFollowedUser())
					.withString("creationDate", friendshipPojo.getCreationDate());
			table.putItem(item);
			success = 1;
		} catch (Exception e) {
			success = 0;
			System.err.println("Create items failed.");
			System.err.println(e.getMessage());

		}
		return success;
	}

	public FriendshipPojo getFreindshipDetailsById(String friendShipId) {
		Table table = dynamoDB.getTable(tableName);

		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("#pk, entityType,followingUser,followedUser,creationDate,SK")
				.withFilterExpression("#pk =:ema").withNameMap(new NameMap().with("#pk", "PK"))
				.withValueMap(new ValueMap().withString(":ema", friendShipId));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				FriendshipPojo friendshipPojo = new FriendshipPojo();
				friendshipPojo.setFriendshipId(item.getString("PK"));
				friendshipPojo.setEntityType(item.getString("entityType"));
				friendshipPojo.setFollowedUser(item.getString("followedUser"));
				friendshipPojo.setFollowingUser(item.getString("followingUser"));
				friendshipPojo.setCreationDate(item.getString("creationDate"));
				return friendshipPojo;
			}
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return null;
	}

	public List<FriendshipPojo> getFreindshipDetailsByUserId(String userId) {
		Table table = dynamoDB.getTable(tableName);
		List<FriendshipPojo> friendshipPojoList = new ArrayList<FriendshipPojo>();
		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("#sk,#en, PK,followingUser,followedUser,creationDate")
				.withFilterExpression("#sk =:sortkey and #en=:entity")
				.withNameMap(new NameMap().with("#sk", "SK").with("#en", "entityType"))
				.withValueMap(new ValueMap().withString(":sortkey", userId).withString(":entity", "friendship"));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				FriendshipPojo friendshipPojo = new FriendshipPojo();
				friendshipPojo.setFriendshipId(item.getString("PK"));
				friendshipPojo.setFollowedUser(item.getString("SK"));
				friendshipPojo.setFollowingUser(item.getString("followingUser"));
				friendshipPojo.setEntityType(item.getString("entityType"));
				friendshipPojo.setCreationDate(item.getString("creationDate"));
				friendshipPojoList.add(friendshipPojo);
			}
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return friendshipPojoList;
	}
}
