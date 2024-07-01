package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.utility.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(InventoryRequest inventoryRequest , int storageId, int quantity , int clientId);

	ResponseEntity<ResponseStructure<InventoryResponse>> readInventory(int productId);

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			int productId, int storageId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory();

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
			int productId, int storageId, int quantity);

}

