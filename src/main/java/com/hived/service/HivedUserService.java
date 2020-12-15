package com.hived.service;

import java.util.Iterator;

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
import com.hived.pojo.HivedUserPojo;

public class HivedUserService {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);

	static String tableName = "hivd";

	public int saveHivedUser(HivedUserPojo hivedUserPojo) {
		Table table = dynamoDB.getTable(tableName);
		int success = 0;
		try {
			Item item = new Item().withPrimaryKey("PK", hivedUserPojo.getUserId())
					.withString("SK", hivedUserPojo.getEmail()).withString("entityType", hivedUserPojo.getEntityType())
					.withString("firstName", hivedUserPojo.getFirstName())
					.withString("lastName", hivedUserPojo.getLastName()).withString("msisdn", hivedUserPojo.getMsisdn())
					.withString("gender", hivedUserPojo.getGender()).withString("email", hivedUserPojo.getEmail())
					.withString("password", hivedUserPojo.getPassword())
					.withString("dateOfBirth", hivedUserPojo.getDateOfBirth())
					.withString("creationDate", hivedUserPojo.getCreationDate())
					.withString("isActive", hivedUserPojo.getIsActive())
					.withString("lastLogin", hivedUserPojo.getLastLogin())
					.withString("deviceType", hivedUserPojo.getDeviceType())
					.withString("userType", hivedUserPojo.getUserType());
			table.putItem(item);
			success = 1;
		} catch (Exception e) {
			success = 0;
			System.err.println("Create items failed.");
			System.err.println(e.getMessage());

		}
		return success;
	}

	 public HivedUserPojo getHivedUserByUserId(String id) {
		Table table = dynamoDB.getTable(tableName);

		ScanSpec scanSpec = new ScanSpec().withProjectionExpression(
				"#em, entityType,firstName,lastName,msisdn,gender,email,password,dateOfBirth,creationDate,isActive,lastLogin,deviceType,userType")
				.withFilterExpression("#em =:ema").withNameMap(new NameMap().with("#em", "PK"))
				.withValueMap(new ValueMap().withString(":ema", id));

		try {
			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iter = items.iterator();
			while (iter.hasNext()) {
				Item item = iter.next();
				HivedUserPojo hivedUserPojo = new HivedUserPojo();
				hivedUserPojo.setUserId(item.getString("PK"));
				hivedUserPojo.setEntityType(item.getString("entityType"));
				hivedUserPojo.setFirstName(item.getString("firstName"));
				hivedUserPojo.setLastName(item.getString("lastName"));
				hivedUserPojo.setMsisdn(item.getString("msisdn"));
				hivedUserPojo.setGender(item.getString("gender"));
				hivedUserPojo.setEmail(item.getString("email"));
				hivedUserPojo.setPassword(item.getString("password"));
				hivedUserPojo.setDateOfBirth(item.getString("dateOfBirth"));
				hivedUserPojo.setCreationDate(item.getString("creationDate"));
				hivedUserPojo.setIsActive(item.getString("isActive"));
				hivedUserPojo.setLastLogin(item.getString("lastLogin"));
				hivedUserPojo.setDeviceType(item.getString("deviceType"));
				hivedUserPojo.setUserType(item.getString("userType"));
				return hivedUserPojo;
			}
		} catch (Exception e) {
			System.err.println("Unable to scan the table:");
			System.err.println(e.getMessage());
		}
		return null;
	}
}
