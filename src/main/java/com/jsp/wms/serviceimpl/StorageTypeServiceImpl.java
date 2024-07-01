package com.jsp.wms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.StorageType;
import com.jsp.wms.exception.StorageTypeNotFoundByIdException;
import com.jsp.wms.mapper.StorageTypeMapper;
import com.jsp.wms.repository.StorageTypeRepository;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.service.StorageTypeService;
import com.jsp.wms.utility.ResponseStructure;

@Service
public class StorageTypeServiceImpl implements StorageTypeService{
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		
		StorageType storageType = storageTypeMapper.mapToStorageTypeRequest(storageTypeRequest, new StorageType());
		
		storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("StorageType Created")
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
			StorageTypeRequest storageTypeRequest, int storageTypeId) {
		
		return storageTypeRepository.findById(storageTypeId).map(exStorageType -> {
			
			exStorageType = storageTypeMapper.mapToStorageTypeRequest(storageTypeRequest, exStorageType);
			
			StorageType storageType = storageTypeRepository.save(exStorageType);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<StorageTypeResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("StorageType Updated")
							.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
		}).orElseThrow(() -> new StorageTypeNotFoundByIdException("StorageType Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllStorageType() {
		
		List<StorageTypeResponse> storageTypes =  storageTypeRepository.findAll().stream()
		.map(storageType -> storageTypeMapper.mapToStorageTypeResponse(storageType))
		.toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<StorageTypeResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("StorageTypes Found")
						.setData(storageTypes));
	}

}