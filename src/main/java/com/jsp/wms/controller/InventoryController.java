package com.jsp.wms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.service.InventoryService;
import com.jsp.wms.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/inventories/storages/{storageId}/clients/{clientId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@RequestBody InventoryRequest inventoryRequest , @PathVariable int storageId , @RequestParam("quantity") int quantity
			, int clientId){
		return inventoryService.createInventory(inventoryRequest , storageId, quantity, clientId);
	}
	
	@GetMapping("/inventories/{productId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> readInventory(@PathVariable int productId){
		return inventoryService.readInventory(productId);
	}
	
	
	@GetMapping("/inventories")
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory(){
		return inventoryService.findAllInventory();
	}
	
	@PutMapping("/inventories/{productId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(@RequestBody InventoryRequest inventoryRequest , @PathVariable int productId ,@PathVariable int storageId){
		return inventoryService.updateInventory(inventoryRequest , productId , storageId);
	}
	
	@PutMapping("/inventories/{productId}/storages/{storageId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(@PathVariable int productId ,@PathVariable int storageId, @RequestParam int quantity){
		return inventoryService.updateInventory(productId , storageId , quantity);
	}
}
