package com.example.FileStorage.daoImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.FileStorage.beans.FileMetadata;
import com.example.FileStorage.beans.FileSearchResponse;
import com.example.FileStorage.dao.FileStorageDao;

@Repository
@Transactional
public class FileStorageDaoImpl implements FileStorageDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	private Log log = LogFactory.getLog(FileStorageDaoImpl.class);

	@Override
	@Transactional
	public void saveFileDetails(String fileNameUrl, String userName, String fileName) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
			entityManager.createNativeQuery("INSERT INTO `FileMetadata`(`userName`, `fileName`, `s3Url`) VALUES(?,?,?)")
					.setParameter(1, userName).setParameter(2, fileName).setParameter(3, fileNameUrl).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		transaction.commit();

	}

	@Override
	public Object getSearchResults(FileMetadata fileMetadata) {
		List<Object[]> results = new ArrayList<>();
		
		try {
			results = entityManager.createNativeQuery("Select `fileName`, `s3Url` from `FileMetadata` where `userName` = ? and `fileName` like ?")
					.setParameter(1, fileMetadata.getUserName()).setParameter(2, "%"+fileMetadata.getFileName()+"%").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<FileSearchResponse> response = new ArrayList<>();
		
		if(results != null && results.size()>0) {
			
			for(Object[] res : results) {
				FileSearchResponse fsr = new FileSearchResponse();
				
				fsr.setFileName((String) res[0]);
				fsr.setS3Url((String) res[1]);
				
				response.add(fsr);
				
			}
			
		}
		
		return response;
	}
}
