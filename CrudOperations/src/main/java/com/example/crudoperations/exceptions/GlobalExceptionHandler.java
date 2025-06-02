package com.example.crudoperations.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> exception1(NullPointerException e){
        return new ResponseEntity<Object>(" No product found for this id ", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> exception2(EmptyResultDataAccessException e){
        return  new ResponseEntity<Object>("Empty ",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> exception4(EmptyListException e){
        return  new ResponseEntity<Object>("List is Empty",HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(com.example.crudoperations.exceptions.DefaultHandlerExceptionResolver.class)
    public ResponseEntity<?> exception3(DefaultHandlerExceptionResolver e){
        return  new ResponseEntity<Object>("Noooo ",HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(EmptyInputExpection.class)
//    public ResponseEntity<String> exception3(EmptyInputException e){
//        return new ResponseEntity<String>("Input Empty",HttpStatus.BAD_REQUEST);
//    }



}
