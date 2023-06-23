package com.lima.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class FileService {
	private static final Logger LOG = LoggerFactory.getLogger(FileService.class);

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${app.awsServices.bucketName}")
	private String s3BucketName;

//	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
//		final File file = new File(multipartFile.getOriginalFilename());
//		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
//			outputStream.write(multipartFile.getBytes());
//		} catch (IOException e) {
//			LOG.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
//		}
//		return file;
//	}
//
//	// @Async annotation ensures that the method is executed in a different thread
//
//	@Async
//	public S3ObjectInputStream findByName(String fileName) {
//		LOG.info("Downloading file with name {}", fileName);
//		return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
//	}
//
//	@Async
//	public void save(final MultipartFile multipartFile) {
//		try {
//			final File file = convertMultiPartFileToFile(multipartFile);
//			final String fileName = LocalDateTime.now() + "_" + file.getName();
//			LOG.info("Uploading file with name {}", fileName);
//			final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
//			amazonS3.putObject(putObjectRequest);
//			Files.delete(file.toPath()); // Remove the file locally created in the project folder
//		} catch (AmazonServiceException e) {
//			LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
//		} catch (IOException ex) {
//			LOG.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
//		}
//	}
	private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); // Generated URL will be valid for 24 hours
        return amazonS3.generatePresignedUrl(s3BucketName, fileName, calendar.getTime(), httpMethod).toString();
    }

    @Async
    public String findByName(String fileName) {
        if (!amazonS3.doesObjectExist(s3BucketName, fileName))
            return "File does not exist";
        LOG.info("Generating signed URL for file name {}", fileName);
        return generateUrl(fileName, HttpMethod.GET);
    }

    @Async
    public String save(String extension) {
        String fileName = UUID.randomUUID().toString() + extension;
        return generateUrl(fileName, HttpMethod.PUT);
    }

}
