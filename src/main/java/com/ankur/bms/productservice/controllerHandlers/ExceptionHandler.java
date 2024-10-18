package com.ankur.bms.productservice.controllerHandlers;


import com.ankur.bms.productservice.dtos.*;
import com.ankur.bms.productservice.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Void> handleArithmeticException(){

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){

        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());



        return new ResponseEntity<>(dto, HttpStatus.OK);


    }
}
