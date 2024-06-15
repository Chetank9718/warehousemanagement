package com.jsp.wms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Address;
import com.jsp.wms.exception.AddressNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AddressMapper;
import com.jsp.wms.repository.AddressRepository;
import com.jsp.wms.repository.WarehouseRepository;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;
import com.jsp.wms.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(AddressRequest addressRequest , int warehouseId) {

		return warehouseRepository.findById(warehouseId).map(warehouse -> {
			Address address = addressMapper.mapToAddress(addressRequest, new Address());

			address.setWarehouse(warehouse);
			
			warehouseRepository.save(warehouse);

			address = addressRepository.save(address);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("Address Created")
							.setData(addressMapper.mapToAddress(address)));
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,
			int addressId) {

		return addressRepository.findById(addressId).map(exAddress -> {

			Address address = addressMapper.mapToAddress(addressRequest, exAddress);

			address = addressRepository.save(address);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Address Updated")
							.setData(addressMapper.mapToAddress(address)));

		}).orElseThrow(()-> new AddressNotFoundByIdException("Address Not Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) {

		return addressRepository.findById(addressId).map(address -> {
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Address Found")
							.setData(addressMapper.mapToAddress(address)));
		}).orElseThrow(()-> new AddressNotFoundByIdException("Address Not Found"));
	}

}
