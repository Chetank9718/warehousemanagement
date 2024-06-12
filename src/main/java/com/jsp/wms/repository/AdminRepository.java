package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.utility.ResponseStructure;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public boolean existsByAdminType(AdminType adminType);

	public Optional<Admin> findByEmail(String username);
	
	public List<Admin> findAllByAdminType(AdminType adminType);
}
