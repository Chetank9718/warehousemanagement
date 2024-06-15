package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;

@Component
public class StorageMapper {

	public Storage mapToStorage(StorageRequest storageRequest, Storage storage) {

		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setCapacityInKg(storageRequest.getCapacityInKg());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());

		return storage;

	}

	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.capacityInKg(storage.getCapacityInKg())
				.materialTypes(storage.getMaterialTypes())
				.availableArea(storage.getAvailableArea())
				.maxAdditionalWeight(storage.getMaxAdditionalWeight())
				.materialTypes(storage.getMaterialTypes())
				.build();



	}
}
