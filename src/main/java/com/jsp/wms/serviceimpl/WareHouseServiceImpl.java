package com.jsp.wms.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.Warehouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByCityException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.WarehouseMapper;
import com.jsp.wms.repository.AddressRepository;
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

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> createWarehouse(WarehouseRequest warehouseRequest) {

		Warehouse warehouse = warehouseRepository.save(warehouseMapper.mapToWarehouse(warehouseRequest, new Warehouse()));

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WarehouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Warehouse Created")
						.setData(warehouseMapper.mapToWarehouseResponse(warehouse)));


	}

	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> updateWarehouse(WarehouseRequest warehouseRequest,
			int warehouseId) {

		return	warehouseRepository.findById(warehouseId).map(exWarehouse -> {

			exWarehouse = warehouseMapper.mapToWarehouse(warehouseRequest, exWarehouse);

			Warehouse warehouse = warehouseRepository.save(exWarehouse);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WarehouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Warehouse Updated")
							.setData(warehouseMapper.mapToWarehouseResponse(warehouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<WarehouseResponse>> findWarehouse(int warehouseId) {

		return warehouseRepository.findById(warehouseId).map(warehouse ->{
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<WarehouseResponse>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Warehouse Found")
							.setData(warehouseMapper.mapToWarehouseResponse(warehouse)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Warehouse Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehouses() {

		List<WarehouseResponse> warehouses = warehouseRepository.findAll().stream()
				.map(warehouseMapper::mapToWarehouseResponse).toList();

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<WarehouseResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Warehouse Found")
						.setData(warehouses));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WarehouseResponse>>> findWarehousesByCity(String city) {

		List<WarehouseResponse> warehouses = addressRepository.findWarehousesByCity(city).stream()
				.map(address -> warehouseMapper.mapToWarehouseAddress(address, address.getWarehouse())).toList();

		if(warehouses.isEmpty())
			throw new WarehouseNotFoundByCityException("Warehouse Not Found");
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<List<WarehouseResponse>>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Warehouse Found By Cities")
							.setData(warehouses));
	}

}
