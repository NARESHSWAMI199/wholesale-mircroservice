package com.wholesale.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity handlerNoEntityFound(NoResultException ex) {
        ex.printStackTrace();
        Map response = new HashMap();
        response.put("message", ex.getMessage());
        response.put("success", true);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handlerException(NullPointerException ex) {
        ex.printStackTrace();
        Map response = new HashMap();
        response.put("message", "Make sure your parameters are not null");
        response.put("success", true);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handlerExceptions(Exception e) {
        e.printStackTrace();
        Map response = new HashMap();
        response.put("message", e.getMessage());
        response.put("success", true);
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}
