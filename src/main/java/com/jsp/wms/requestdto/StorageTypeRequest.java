package com.jsp.wms.requestdto;

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
public class StorageTypeRequest {

	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityInWeight;
}
