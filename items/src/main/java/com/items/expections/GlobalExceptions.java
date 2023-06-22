package com.items.expections;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptions {


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> NullPointerExceptionHandler(NullPointerException e) {
        e.printStackTrace();
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", "May be some credentials are null.");
        return ResponseEntity.status(400).body(map);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handlerIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", "May be some credentials are null.");
        return ResponseEntity.status(400).body(map);
    }




    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> EntityNotFoundException(EntityNotFoundException e) {
        System.out.println(">>>>>>>> hello");
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", e.getMessage());
        return ResponseEntity.status(400).body(map);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>ExceptionHandler(Exception e) {
        e.printStackTrace();
        Map map = new HashMap();
        map.put("success", false);
        map.put("error", e.getMessage());
        return ResponseEntity.status(500).body(map);
    }


}
