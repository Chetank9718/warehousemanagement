package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Warehouse;


public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

	
}
