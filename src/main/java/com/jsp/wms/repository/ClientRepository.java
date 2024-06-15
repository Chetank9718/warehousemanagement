package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
