package com.jsp.wms.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.mapper.WarehouseMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.WarehouseRequest;
import com.jsp.wms.responsedto.WarehouseResponse;
import com.jsp.wms.service.WarehouseService;
import com.jsp.wms.utility.ResponseStructure;

@Service
public class WarehouseServiceImpl implements WarehouseService{

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Autowired
	private WarehouseMapper warehouseMapper;

	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest) {

		Warehouse warehouse = warehouseRepository.save(warehouseMapper.mapToWarehouse(warehouseRequest, new Warehouse()));
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WarehouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Warehouse Created")
						.setData(warehouseMapper.mapToWarehouseResponse(warehouse)));


	}

}
