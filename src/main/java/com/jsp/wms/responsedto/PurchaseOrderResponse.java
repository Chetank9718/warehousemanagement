package com.jsp.wms.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderResponse {

	private int orderId;
	private int orderQuantity;
	private String invoiceLink;
	private String status;
	private int customerId;
}
