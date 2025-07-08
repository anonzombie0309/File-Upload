package com.example.FileStorage.controller;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileStorage.beans.BaseResponse;
import com.example.FileStorage.beans.FileMetadata;
import com.example.FileStorage.service.FileStorageService;
import com.example.FileStorage.util.SecurityUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
public class FileStorageController {
	
	@Autowired
	FileStorageService fileService;
	
	private Log log = LogFactory.getLog(FileStorageController.class);

	@Autowired
	Environment environment;
	
	@Autowired
	BaseResponse baseResponse;
	
	@Autowired
	SecurityUtil securityUtil;

	@GetMapping("/test")
	public String test() {

		log.info("Successfully called api");

		String port = environment.getProperty("local.server.port");
		return ("Success, failed" + port);

	}

	
	@PostMapping("/upload")
	public BaseResponse upload(@RequestHeader(required = true) String userName,
			@RequestParam(name = "file", required = true) MultipartFile file) {


			try {
				log.info("***********start of upload api in FileStorageController" + new Date());

					try {
			            // Convert MultipartFile to File
			            File file1 = convertMultiPartToFile(file);
			            baseResponse = fileService.uploadFile(file1,userName);
			            file1.delete(); // Clean up after the upload
			            
			            return baseResponse;
			        } catch (Exception e) {
			        	baseResponse = securityUtil.setMessageReponse(-2);
			        	baseResponse.setDetails("File upload failed. " + e.getMessage());
			            return baseResponse;
			        }
				}

				
			 catch (Exception e) {
				baseResponse = securityUtil.setMessageReponse(-2);
				baseResponse.setDetails(null);
				log.info(e.toString());
				return baseResponse;
			 }
			}
		

	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
	
	
	
	@PostMapping("/search")
	public BaseResponse search(@RequestBody(required = true) FileMetadata fileMetadata) {


			try {
				log.info("***********start of search api in FileStorageController" + new Date());

					try {
						
						baseResponse = fileService.search(fileMetadata);
			            
			            return baseResponse;
			        } catch (Exception e) {
			        	baseResponse = securityUtil.setMessageReponse(-2);
			        	baseResponse.setDetails(null);
			        	baseResponse.setDetails("File upload failed. " + e.getMessage());
			            return baseResponse;
			        }
				}

				
			 catch (Exception e) {
				baseResponse = securityUtil.setMessageReponse(-2);
				baseResponse.setDetails(null);
				log.info(e.toString());
				return baseResponse;
			 }
			}

	
//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(
//            @RequestParam String userName,
//            @RequestParam MultipartFile file) {
//        fileService.uploadFile(userName, file);
//        return ResponseEntity.ok("Uploaded successfully");
//    }

//    @GetMapping("/search")
//    public ResponseEntity<Object> search(
//            @RequestParam String userName,
//            @RequestParam String searchTerm) {
//        return ResponseEntity.ok(fileService.searchFiles(userName, searchTerm));
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(
//            @RequestParam String userName,
//            @RequestParam String fileName) {
//        Resource res = fileService.downloadFile(userName, fileName);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename="" + fileName + """)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(res);
//    }
}
