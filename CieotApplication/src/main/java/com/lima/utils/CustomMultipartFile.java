package com.lima.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CustomMultipartFile implements MultipartFile {
    private byte[] input;
    private String fileName;

    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CustomMultipartFile(byte[] input) {
        this.input = input;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return this.fileName;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return input == null || input.length == 0;
    }

    @Override
    public long getSize() {
        return input.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return input;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input);
    }

    @Override
    public void transferTo(File destination) throws IOException, IllegalStateException {
        try(FileOutputStream fos = new FileOutputStream(destination)) {
            fos.write(input);
        }
    }

}