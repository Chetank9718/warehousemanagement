package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.utility.ResponseStructure;
import com.jsp.wms.utility.SimpleStructure;

public interface StorageService {
	
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int wareHouseId, int noOfStorageUnits, int storageTypeId);

	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest);

	
}
