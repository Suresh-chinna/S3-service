package com.s3.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@SpringBootApplication
public class S3ServiceApplication {
	/*
	 * @Value("${spring.cloud.aws.s3.credentials.access-key}") private String
	 * accessKey;
	 * 
	 * @Value("${spring.cloud.aws.s3.credentials.secret-key}") private String
	 * secretKey;
	 * 
	 * @Value("${spring.cloud.aws.s3.credentials.region.static}") private String
	 * region;
	 */

	public static void main(String[] args) {
		SpringApplication.run(S3ServiceApplication.class, args);
	}
	
	

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials("AKIAWON4OXQ7GLTRXJQV", "2oFUlSxPOM2tiPuGM4Vrpa/0F+nR72DyybWzqoH/");
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
