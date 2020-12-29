package com.hived.presignedURL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URL;

public class GeneratePresignedURL {

	public String getPresignedURL(String objectKey) {
		Regions clientRegion = Regions.AP_SOUTH_1;
		String bucketName = "my-profile-image";

		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).build();

			// Set the pre-signed URL to expire after one hour.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);

			// System.out.println("Generating pre-signed URL.");
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
					objectKey).withContentType("image/jpeg").withExpiration(expiration).withMethod(HttpMethod.PUT);
			URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

			// System.out.println("Pre-Signed URL: " + url);
			return url.toString();
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		return null;
	}
}
