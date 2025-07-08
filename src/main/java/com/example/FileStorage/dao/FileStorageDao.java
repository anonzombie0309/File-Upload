package com.example.FileStorage.dao;

import com.example.FileStorage.beans.FileMetadata;

public interface FileStorageDao {

	void saveFileDetails(String fileNameUrl, String userName, String fileName);

	Object getSearchResults(FileMetadata fileMetadata);

}
