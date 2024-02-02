package com.lima.dto;

public class BucketAndFileDTORequest {
	private String bucketKey;
	private String fileName;

	public BucketAndFileDTORequest(String bucketKey, String fileName) {
		super();
		this.bucketKey = bucketKey;
		this.fileName = fileName;
	}

	public String getBucketKey() {
		return bucketKey;
	}

	public void setBucketKey(String bucketKey) {
		this.bucketKey = bucketKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
