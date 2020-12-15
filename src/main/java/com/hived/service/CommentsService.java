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
import com.hived.pojo.CommentsPojo;

public class CommentsService {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "hivd";

	public int saveComments(CommentsPojo commentsPojo) {
		Table table = dynamoDB.getTable(tableName);
		int success = 0;
		try {
			Item item = new Item().withPrimaryKey("PK", commentsPojo.getCommentId())
					.withString("SK", commentsPojo.getProductId()).withString("entityType", commentsPojo.getEntityType())
					.withString("commentsText", commentsPojo.getCommentsText())
					.withString("actionType", commentsPojo.getActionType())
					.withString("creationDate", commentsPojo.getCreationDate());
			table.putItem(item);
			success = 1;
		} catch (Exception e) {
			success = 0;
			System.err.println("Comments Create items failed.");
			System.err.println(e.getMessage());

		}
		return success;
	}
	
	public CommentsPojo getProductByProductId(String commenttId) {
		Table table = dynamoDB.getTable(tableName);

		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("#pk, entityType,commentsText,actionType,creationDate,SK")
				.withFilterExpression("#pk =:cId").withNameMap(new NameMap().with("#pk", "PK"))
				.withValueMap(new ValueMap().withString(":cId", commenttId));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				CommentsPojo commentsPojo = new CommentsPojo();
				commentsPojo.setCommentId(item.getString("PK"));
				commentsPojo.setProductId(item.getString("SK"));
				commentsPojo.setEntityType(item.getString("entityType"));
				commentsPojo.setCommentsText(item.getString("commentsText"));
				commentsPojo.setActionType(item.getString("actionType"));
				commentsPojo.setCreationDate(item.getString("creationDate"));
				return commentsPojo;
			}
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return null;
	}

	public List<CommentsPojo> getProductByUserId(String userId) {
		Table table = dynamoDB.getTable(tableName);
		List<CommentsPojo> commentsPojoList = new ArrayList<CommentsPojo>();
		ScanSpec scanSpec = new ScanSpec()
				.withProjectionExpression("#sk,#en, PK,productDesc,modelNo,brandName,technology,creationDate")
				.withFilterExpression("#sk =:sortkey and #en=:entity")
				.withNameMap(new NameMap().with("#sk", "SK").with("#en", "entityType"))
				.withValueMap(new ValueMap().withString(":sortkey", userId).withString(":entity", "product"));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				CommentsPojo commentsPojo = new CommentsPojo();
				commentsPojo.setCommentId(item.getString("PK"));
				commentsPojo.setProductId(item.getString("SK"));
				commentsPojo.setEntityType(item.getString("entityType"));
				commentsPojo.setCreationDate(item.getString("creationDate"));
				commentsPojoList.add(commentsPojo);
			}
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return commentsPojoList;
	}
}
