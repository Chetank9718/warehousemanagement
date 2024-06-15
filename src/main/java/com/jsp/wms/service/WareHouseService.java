package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
import com.jsp.wms.utility.ResponseStructure;

public interface WarehouseService {

	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest);

	public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			int warehouseId);

	public ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(int warehouseId);

	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses();

	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCity(String city);

	

}
