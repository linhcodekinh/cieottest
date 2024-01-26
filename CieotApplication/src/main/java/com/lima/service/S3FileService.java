package com.lima.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lima.common.MyConstants;

@Service
public class S3FileService {
	private static final Logger LOG = LoggerFactory.getLogger(S3FileService.class);

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${app.awsServices.bucketNameTest}")
	private String s3BucketPart;

	@Value("${app.awsServices.bucketUser}")
	private String s3BucketUser;

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (IOException e) {
			LOG.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
		}
		return file;
	}

	// @Async annotation ensures that the method is executed in a different thread

//	@Async
//	public S3ObjectInputStream findByName(String fileName) {
//		LOG.info("Downloading file with name {}", fileName);
//		return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
//	}

	@Async
	public void save(String bucketName, final MultipartFile multipartFile, String filePath, String fileName) {
		try {
			final File file = convertMultiPartFileToFile(multipartFile);
			String pathName = "";
			if ("".equals(filePath)) {
				pathName = fileName;
			} else
				pathName = filePath + "/" + fileName;
			LOG.info("Uploading file with name {}", pathName);
			PutObjectRequest putObjectRequest = null;
			if (MyConstants.BUCKET_USER.equals(bucketName)) {
				putObjectRequest = new PutObjectRequest(s3BucketUser, pathName, file);
				amazonS3.putObject(putObjectRequest);
			}
			// Files.delete(file.toPath()); // Remove the file locally created in the
			// project folder
		} catch (AmazonServiceException e) {
			LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
		}
//		catch (IOException ex) {
//			LOG.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
//		}
	}

	private String generateUrl(String fileName, HttpMethod httpMethod) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
		// String presignedUrl = amazonS3.generatePresignedUrl(s3BucketUser, fileName,
		// calendar.getTime(), httpMethod).toString();
		// amazonS3.putObject(presignedUrl);
		return amazonS3.generatePresignedUrl(s3BucketUser, fileName, calendar.getTime(), httpMethod).toString();
	}

	@Async
	public String findByName(String fileName) {
		if (!amazonS3.doesObjectExist(s3BucketUser, fileName))
			return "File does not exist";
		LOG.info("Generating signed URL for file name {}", fileName);
		return generateUrl(fileName, HttpMethod.GET);
	}

	@Async
	public String save(String extension) {
		// change file name here
		String fileName = "image/" + UUID.randomUUID().toString() + extension;
		return generateUrl(fileName, HttpMethod.PUT);
	}

}
