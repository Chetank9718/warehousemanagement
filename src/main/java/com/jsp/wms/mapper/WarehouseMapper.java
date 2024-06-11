package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;

@Component
public class WarehouseMapper {

	public Warehouse mapToWarehouse(WarehouseRequest warehouseRequest , Warehouse warehouse) {
		warehouse.setName(warehouseRequest.getName());
		return warehouse;
	}
	
	public WarehouseResponse mapToWarehouseResponse(Warehouse warehouse) {
		return WarehouseResponse.builder()
				.warehouseId(warehouse.getWarehouseId())
				.name(warehouse.getName())
				.totalCapacity(0)
				.build();
	}
}
