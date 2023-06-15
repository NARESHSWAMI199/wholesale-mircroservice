package com.auth.payload;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {

    String message;
    boolean success;
    HttpStatus status;
}
