package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientNotFoundByIdException extends RuntimeException{

	private String message;
}
