package com.example.FileStorage.serviceImpl;

import com.example.FileStorage.beans.BaseResponse;
import com.example.FileStorage.beans.FileMetadata;
import com.example.FileStorage.dao.FileStorageDao;
import com.example.FileStorage.service.FileStorageService;
import com.example.FileStorage.service.S3Service;
import com.example.FileStorage.util.SecurityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	private S3Service s3Service;

	@Autowired
	FileStorageDao dao;
	@Autowired
	SecurityUtil util;


	@Override
	public BaseResponse uploadFile(File file1, String userName) {

		 BaseResponse baseResponse = new BaseResponse();
		 
			 try {
				String fileNameUrl = s3Service.uploadFile(file1);
				 
				 dao.saveFileDetails(fileNameUrl,userName,file1.getName());
				 
				 baseResponse = util.setMessageReponse(1);
		            baseResponse.setDetails(fileNameUrl);
			} catch (Exception e) {
				 baseResponse = util.setMessageReponse(-2);
		            baseResponse.setDetails(e);
//				e.printStackTrace();
			}
			
     
		return baseResponse;
	}
	
	@Override
	public BaseResponse search(FileMetadata fileMetadata) {
		BaseResponse baseResponse = new BaseResponse();
		 
		 try {
			 
			 baseResponse = util.setMessageReponse(1);
			 
			 baseResponse.setDetails(dao.getSearchResults(fileMetadata));
			 
			
		} catch (Exception e) {
			 baseResponse = util.setMessageReponse(-2);
	            baseResponse.setDetails(e);
//			e.printStackTrace();
		}
		

	return baseResponse;
	}
}
