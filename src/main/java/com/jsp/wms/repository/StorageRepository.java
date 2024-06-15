package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.responsedto.StorageResponse;

public interface StorageRepository extends JpaRepository<Storage, Integer>{

	public Optional<Storage> findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(double capacityInKg , double lengthInMeters , double breadthInMeters , double heightInMeters);
}
