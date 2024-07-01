package com.jsp.wms.requestdto;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryRequest {

	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	

	List<MaterialTypes> materialTypes;
	private int sellerId;
}
