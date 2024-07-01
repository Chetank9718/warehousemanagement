package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;
import com.jsp.wms.utility.ResponseStructure;
import com.jsp.wms.utility.SimpleStructure;

@RestController
@RequestMapping("/api/v1")
public class StorageController {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private StorageRepository storageRepository;
	
	@PreAuthorize("hasAuthority('CREATE_STORAGE')")
	@PostMapping("/warehouses/{wareHouseId}/storages")
	public ResponseEntity<SimpleStructure<String>> createStorage(@RequestBody  StorageRequest storageRequest ,
			@PathVariable int wareHouseId, @RequestParam("no_of_storage_units") int noOfStorageUnits , int storageTypeId ){
		return storageService.createStorage(storageRequest , wareHouseId , noOfStorageUnits , storageTypeId);
	}
	
	
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@PathVariable int storageId, @RequestBody StorageRequest storageRequest) {
	
		return storageService.updateStorage(storageId, storageRequest);
	}
	
//	@GetMapping("/findfirststorage")
//	public Storage findFirstStorage() {
//		return storageRepository.findFirstByCapacityInWeightAndLengthInMetersAndBreadthInMetersAndHeightInMeters(150, 10, 7.5, 8.5).orElseThrow(() -> new RuntimeException("Not Found"));
//	}
	
	
}
