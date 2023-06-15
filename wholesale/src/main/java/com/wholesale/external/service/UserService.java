package com.wholesale.external.service;


import com.wholesale.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE")
/** this is declarative approach */
public interface UserService {


    @GetMapping("/users/{userId}")
    User getUserById(@PathVariable Integer userId);

}
