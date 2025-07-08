package com.example.FileStorage.beans;

import lombok.AllArgsConstructor;
import lombok.Data;


public class FileSearchResponse {
    private String fileName;
    private String s3Url;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getS3Url() {
		return s3Url;
	}
	public void setS3Url(String s3Url) {
		this.s3Url = s3Url;
	}
    
    
}
