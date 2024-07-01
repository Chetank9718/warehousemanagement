package com.jsp.wms.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.AddressNotFoundByIdException;
import com.jsp.wms.exception.AdminNotFoundByEmailException;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.InventoryNotFoundByIdException;
import com.jsp.wms.exception.PurchaseOrderNotFoundByIdException;
import com.jsp.wms.exception.StorageTypeNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByCityException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;

@RestControllerAdvice
public class AplicationExceptionHandler {
	

	public ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status , String message , String rootCause){
		return ResponseEntity.status(status)
				.body(new ErrorStructure<>()
						.setStatus(status.value())
						.setMessage(message)
						.setRootcause(rootCause));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalOperationException(IllegalOperationException ex){
		return errorResponse(HttpStatus.FOUND, ex.getMessage(), "Super_Admin Already Exists");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleWarehouseNotFoundByIdException(WarehouseNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "WareHouse Not Found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFoundByEmailException(AdminNotFoundByEmailException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Admin Email Not Found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAddressNotFoundByIdException(AddressNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Address Not Found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleWarehouseNotFoundByCityException(WarehouseNotFoundByCityException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Warehouse Not Found By City");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleStorageTypeNotFoundByIdException(StorageTypeNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "StorageType Not Found");
	}
	
	
	public ResponseEntity<ErrorStructure<String>> handleInventoryNotFoundByIdException(InventoryNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Inventory Not Found By Id");
	}
	
	public ResponseEntity<ErrorStructure<String>> handlePurchaseOrderNotFoundByIdException(PurchaseOrderNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "PurchaseOrder Not Found By Id");
	}
	

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<ObjectError> errors = ex.getAllErrors();
			
		Map<String, String> allErrors = new HashMap<>();
		
		errors.forEach(error ->{
			FieldError fieldError = (FieldError) error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorStructure<List<String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setMessage("Invalid Message")
						.setRootcause(allErrors));
						
	}
	
 
}
