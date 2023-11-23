package com.productservice.productservice.Controller;

import com.productservice.productservice.DTO.ExceptionDTO;
import com.productservice.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException
    ){

        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}