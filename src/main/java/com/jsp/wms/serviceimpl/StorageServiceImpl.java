package com.jsp.wms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.exception.StorageNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.StorageMapper;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;
import com.jsp.wms.utility.ResponseStructure;
import com.jsp.wms.utility.SimpleStructure;

@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private WarehouseRepository wareHouseRespository;

	@Autowired
	private StorageMapper storageMapper;

	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int wareHouseId,
			int noOfStorageUnits) {

	Warehouse wareHouse =  wareHouseRespository.findById(wareHouseId).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse Not Found"));

	List<Storage> storages = new ArrayList<Storage>();

	int count = 0;

	while(noOfStorageUnits > 0) {

		Storage storage  = storageMapper.mapToStorage(storageRequest, new Storage());



		storage.setMaxAdditionalWeight(storageRequest.getCapacityInKg());
		storage.setAvailableArea(storageRequest.getLengthInMeters() * storageRequest.getBreadthInMeters() * storageRequest.getHeightInMeters());
		storage.setWareHouse(wareHouse);

		wareHouse.setTotalCapacityInKg(storage.getCapacityInKg() * noOfStorageUnits + wareHouse.getTotalCapacityInKg());

		storages.add(storage);
		count++;
		noOfStorageUnits --;
	}

	storageRepository.saveAll(storages);
	//wareHouseRespository.save(wareHouse);

	
	
	return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
			.setStatus(HttpStatus.CREATED.value())
			.setMesssage(""+count + " Storages created"));

	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest) {

		return storageRepository.findById(storageId).map(existingStorage -> {

			existingStorage = storageMapper.mapToStorage(storageRequest, existingStorage);
			storageRepository.save(existingStorage);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage updsates")
					.setData(storageMapper.mapToStorageResponse(existingStorage)));


		}).orElseThrow(()-> new StorageNotFoundByIdException("Storage Not Found"));
	}

	
}




