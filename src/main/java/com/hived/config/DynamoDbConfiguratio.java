//package com.hived.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
////import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
////import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
////import com.amazonaws.services.simplesystemsmanagement.*;
////import com.amazonaws.services.simplesystemsmanagement.model.*;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
//import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
//import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
//import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
//
//@Configuration
//public class DynamoDbConfiguratio {
//
////	aws ssm get-parameters --names /myfirstapp/accssd /myfirstapp/sctkey ----- command
//// "ARN": "arn:aws:ssm:ap-south-1:735664077538:parameter/myfirstapp/accssd"
//// "ARN": "arn:aws:ssm:ap-south-1:735664077538:parameter/myfirstapp/sctkey"
//	static AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();
//	 
////	AmazonDynamoDB getDynamoDbClient() {
////	    BasicAWSCredentials differentAWSCreds = new BasicAWSCredentials(
////	            getParameter("/myfirstapp/accssd"),
////	            getParameter("/myfirstapp/sctkey"));
////	 
////	    final AmazonDynamoDB client = AmazonDynamoDBClient.builder()
////	            .withCredentials(new AWSStaticCredentialsProvider(differentAWSCreds))
////	            .withRegion(Regions.AP_SOUTH_1)
////	            .build();
////	    return client;
////	}
//	public static void main(String [] args) {
//		GetParameterResult g=getParameter("/myfirstapp/accssd");
//		System.out.println(g);
//	}
//	public static GetParameterResult getParameter(String parameterName) {
//	    GetParameterRequest request = new GetParameterRequest();
//	    request.setName(parameterName);
//	    request.setWithDecryption(true);
//	    return ssm.getParameter(request);
//	}
//
///*currently running method start*/	
//	@Bean
//	public DynamoDBMapper dynamoDBMapper() {
//		return new DynamoDBMapper(buildAmazonDynamoDB());
//	}

//
//	public AmazonDynamoDB buildAmazonDynamoDB() {
//		AmazonDynamoDB client;
////		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA2WSH67LRM27Z25E7",
////				"jWGoShMWcjBTo8AkYLsb6pL8vJKXnRppwYbDA5mb");
////		client = AmazonDynamoDBClient.builder().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
////				.withRegion(Regions.AP_SOUTH_1).build();
//		client = AmazonDynamoDBClientBuilder.standard().build();
//		return client;
//	}
///*end*/
//}
