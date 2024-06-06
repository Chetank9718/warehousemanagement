package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {

	public ResponseEntity<ResponseStructure<AdminResponse>> saveAdmin(AdminRequest adminRequest); 
}
