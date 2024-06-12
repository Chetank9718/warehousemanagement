package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Address;

import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;

@Component
public class AddressMapper {

	
	public Address mapToAddress(AddressRequest addressRequest , Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setCountry(addressRequest.getCountry());
		address.setState(addressRequest.getState());
		address.setPincode(addressRequest.getPincode());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitude(addressRequest.getLongitude());
		
		return address;
	}

	public AddressResponse mapToAddress(Address address) {
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.addressLine(address.getAddressLine())
				.city(address.getCity())
				.state(address.getState())
				.pincode(address.getPincode())
				.country(address.getCountry())
				.longtitude(address.getLongitude())
				.latitude(address.getLatitude())
				.build();
	
	}
}
