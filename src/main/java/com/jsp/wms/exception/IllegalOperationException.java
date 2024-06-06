package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
public class IllegalOperationException extends RuntimeException {

	private String message;
}
