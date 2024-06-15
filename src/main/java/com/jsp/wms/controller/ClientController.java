package com.jsp.wms.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ApiKeyResponse;
import com.jsp.wms.responsedto.ClientResponse;
import com.jsp.wms.service.ClientService;
import com.jsp.wms.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@RequestBody ClientRequest clientRequest , @PathVariable int clientId){
		return clientService.updateClient(clientRequest , clientId);
	}
}
