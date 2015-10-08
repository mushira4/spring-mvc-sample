package br.com.spring.mvc.basics.config;

import org.springframework.context.annotation.Bean;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class AmazonConfiguration {

	@Bean
	private AmazonS3Client amazonClient() {
		AWSCredentials credentials = new BasicAWSCredentials("", "");
		AmazonS3Client newClient = new AmazonS3Client(credentials, new ClientConfiguration());
		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		newClient.setEndpoint("http://localhost:9444/s3");
		return newClient;
	}
	
}
