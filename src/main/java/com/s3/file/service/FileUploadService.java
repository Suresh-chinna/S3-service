package com.s3.file.service;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class FileUploadService {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${spring.cloud.aws.s3.bucket.name}")
	private String bucketName;

	public String uploadFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {
			InputStream inputStream = file.getInputStream();
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, null);
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
		}
		return fileName;
	}

	public String getObject(String fileName) {
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileName);
			S3Object s3Object = amazonS3.getObject(getObjectRequest);
			FileOutputStream fileOutputStream = new FileOutputStream("C:/suresh/pdf/" + fileName);
			S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
			IOUtils.copy(objectInputStream, fileOutputStream);
			objectInputStream.close();
			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
