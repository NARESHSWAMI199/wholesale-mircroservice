package com.auth.controller;


import com.auth.dto.UserDto;
import com.auth.entity.User;
import com.auth.services.UserService;
import com.auth.utils.Util;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


public class BaseController {


    @Autowired
    protected UserService userService;

    @Autowired
    protected ModelMapper modelMapper;


    protected UserDto convertToDto(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }


    protected User convertToEntity(Integer userId, UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = modelMapper.map(userDto, User.class);
        user.setSlug(UUID.randomUUID().toString());
        user.setUpdatedAt(Util.getCurrentTime());
        user.setCreatedAt(Util.getCurrentTime());
        user.setCreatedBy(userId);
        user.setUpdatedBy(userId);
        return user;
    }


}
