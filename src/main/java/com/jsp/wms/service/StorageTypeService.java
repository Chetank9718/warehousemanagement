package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.utility.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest,
			int storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType();

}
