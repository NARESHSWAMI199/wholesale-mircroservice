package com.auth.exceptions;



/** global exception handler */
import com.auth.payload.APIResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /** this will accor if you got this exception on any point */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<APIResponse> handlerNoEntityFound(NoResultException ex){
        APIResponse apiResponse = APIResponse.builder().message("Entity not found.").success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse> handlerException(NullPointerException ex){
        APIResponse apiResponse = APIResponse.builder().message("Make sure your parameters are not null.").success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handlerException(Exception ex){
        APIResponse apiResponse = APIResponse.builder().message(ex.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }


}
