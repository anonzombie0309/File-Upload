package com.example.FileStorage.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.example.FileStorage.beans.BaseResponse;
import com.example.FileStorage.beans.FileMetadata;

public interface FileStorageService {


//	Object searchFiles(String userName, String searchTerm);

	BaseResponse uploadFile(File file1, String userName);

	BaseResponse search(FileMetadata fileMetadata);

}
