package com.hived.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.hived.pojo.ProductPostPojo;

public class ProductPostService {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	static String tableName = "hivd";

	public List<ProductPostPojo> getReviewListByUserId(String userId, String pValue1,String pValue2) {
		
		List<ProductPostPojo> list=new ArrayList<ProductPostPojo>();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS("product_post"));
		eav.put(":val2", new AttributeValue().withS(userId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("entityType = :val1 and SK = :val2").withExpressionAttributeValues(eav);
		scanExpression.setLimit(6);
		ScanResultPage<ProductPostPojo> scanResult;
		if(pValue1==null || pValue1.equals("") || pValue2==null || pValue2.equals("")) {
		scanResult= mapper.scanPage(ProductPostPojo.class, scanExpression);
		for (ProductPostPojo p : scanResult.getResults()) {
			ProductPostPojo pojo=new ProductPostPojo();
			pojo.setReviewId(p.getReviewId());
			pojo.setUserId(p.getSK());
			pojo.setTitle(p.getTitle());
			pojo.setSub_title(p.getSub_title());
			pojo.setRating(p.getRating());
			pojo.setProfile_url(p.getProfile_url());
			pojo.setUsername(p.getUsername());
			pojo.setImages(p.getImages());
			pojo.setCreationDate(p.getCreationDate());
			if(scanResult.getLastEvaluatedKey()==null) {
				pojo.setPaginationValue1(null);
				pojo.setPaginationValue2(null);
			}else {
				pojo.setPaginationValue1(scanResult.getLastEvaluatedKey().get("SK").getS());
				pojo.setPaginationValue2(scanResult.getLastEvaluatedKey().get("PK").getS());
			}
			list.add(pojo);
		}
		}else {
			Map<String, AttributeValue> startKey = new HashMap<String, AttributeValue>();
			startKey.put("SK", new AttributeValue().withS(pValue1));
			startKey.put("PK", new AttributeValue().withS(pValue2));
			scanExpression.setExclusiveStartKey(startKey);
			scanResult= mapper.scanPage(ProductPostPojo.class, scanExpression);
			for (ProductPostPojo p : scanResult.getResults()) {
				ProductPostPojo pojo=new ProductPostPojo();
				pojo.setReviewId(p.getReviewId());
				pojo.setUserId(p.getSK());
				pojo.setTitle(p.getTitle());
				pojo.setSub_title(p.getSub_title());
				pojo.setRating(p.getRating());
				pojo.setProfile_url(p.getProfile_url());
				pojo.setUsername(p.getUsername());
				pojo.setImages(p.getImages());
				pojo.setCreationDate(p.getCreationDate());
				if(scanResult.getLastEvaluatedKey()==null) {
					pojo.setPaginationValue1(null);
					pojo.setPaginationValue2(null);
				}else {
					pojo.setPaginationValue1(scanResult.getLastEvaluatedKey().get("SK").getS());
					pojo.setPaginationValue2(scanResult.getLastEvaluatedKey().get("PK").getS());
				}
				list.add(pojo);
			}
		}
		return list;
	}
	
}
