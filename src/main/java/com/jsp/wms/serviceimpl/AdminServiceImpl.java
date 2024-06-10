package com.jsp.wms.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.utility.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {

		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN)) {

			throw new IllegalOperationException("Super_Admin Already Present");

		}else {

			Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.SUPER_ADMIN);

			admin = adminRepository.save(admin);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("Super Admin Created")
							.setData(adminMapper.mapToAdminResponse(admin)));	
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest , int wareHouseId) {

		return wareHouseRepository.findById(wareHouseId).map(warehouse ->{
			Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			
			 admin = adminRepository.save(admin);
			 
			 warehouse.setAdmin(admin);
			 
			 wareHouseRepository.save(warehouse);
			 
			 return ResponseEntity.status(HttpStatus.CREATED)
					 .body(new ResponseStructure<AdminResponse>()
							 .setStatus(HttpStatus.CREATED.value())
							 .setMessage("Admin Created")
							 .setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Not Found"));
	}


}
