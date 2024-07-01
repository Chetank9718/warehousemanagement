package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.PurchaseOrderRequest;
import com.jsp.wms.responsedto.PurchaseOrderResponse;
import com.jsp.wms.utility.ResponseStructure;

public interface PurchaseOrderService {

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest, int productId);

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> findPurchaseOrder(int orderId);

	ResponseEntity<ResponseStructure<List<PurchaseOrderResponse>>> findAllPurchaseOrder();

}
