package com.auth.controller;


import com.auth.dto.UserDto;
import com.auth.entity.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController extends BaseController {


    /** we are using here circuit breaker in case api is down then we can call another method. or don't send the request */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
        UserDto userDto = convertToDto(userService.getUserById(userId));
        return ResponseEntity.status(200).body(userDto);
    }





    @GetMapping("/slug/{slug}")
    public ResponseEntity<UserDto> getAllUser(@PathVariable String slug) {
        UserDto userDto = convertToDto(userService.findUserBySlug(slug));
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userService.getAllUser().stream().map(user -> convertToDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Successfully deleted.");
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user) {
        UserDto userDto = convertToDto(userService.saveUser(user));
        return ResponseEntity.status(200).body(userDto);
    }
}
