package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;

@Component
public class WarehouseMapper {
	
	@Autowired
	private AddressMapper addressMapper;

	public Warehouse mapToWarehouse(WarehouseRequest warehouseRequest , Warehouse warehouse) {
		warehouse.setName(warehouseRequest.getName());
		return warehouse;
	}
	
	public WarehouseResponse mapToWarehouseResponse(Warehouse warehouse) {
		return WarehouseResponse.builder()
				.warehouseId(warehouse.getWarehouseId())
				.name(warehouse.getName())
				.totalCapacity(warehouse.getTotalCapacityInKg())
				.build();
	}
	
	
	public WarehouseResponse mapToWarehouseAddress(Address address , Warehouse warehouse) {
		
		return WarehouseResponse.builder()

				.warehouseId(warehouse.getWarehouseId())
				.name(warehouse.getName())
				.addressResponse(addressMapper.mapToAddress(address))
				.build();
	}
}
