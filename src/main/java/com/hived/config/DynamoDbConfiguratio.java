package com.hived.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfiguratio {

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}

	public AmazonDynamoDB buildAmazonDynamoDB() {
		AmazonDynamoDB client;
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA2WSH67LRM27Z25E7",
				"jWGoShMWcjBTo8AkYLsb6pL8vJKXnRppwYbDA5mb");
		client = AmazonDynamoDBClient.builder().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.AP_SOUTH_1).build();
		return client;
	}

}
