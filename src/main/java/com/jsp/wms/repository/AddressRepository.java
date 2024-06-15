package com.jsp.wms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer>{

	public List<Address> findWarehousesByCity(String City);
}
