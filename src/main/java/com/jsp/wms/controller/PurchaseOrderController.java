package com.jsp.wms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.PurchaseOrderRequest;
import com.jsp.wms.responsedto.PurchaseOrderResponse;
import com.jsp.wms.service.PurchaseOrderService;
import com.jsp.wms.utility.ResponseStructure;

@RestController
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@PostMapping("/inventories/{productId}/purchaseorders")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest ,@PathVariable int productId){
		return purchaseOrderService.createPurchaseOrder(purchaseOrderRequest , productId);
	}

	@GetMapping("/purchaseorders/{orderId}")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findPurchaseOrder(@PathVariable int orderId){
		return purchaseOrderService.findPurchaseOrder(orderId);
	}

	@GetMapping("/purchaseorders")
	public ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findAllPurchaseOrder(){
		return purchaseOrderService.findAllPurchaseOrder();
	}
}
