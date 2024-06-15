package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StorageNotFoundByIdException extends RuntimeException{

	private String message;
}
