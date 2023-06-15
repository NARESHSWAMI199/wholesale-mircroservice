package com.wholesale.controllers;

import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;
import com.wholesale.services.WholesaleServiceImpl;
import com.wholesale.utils.Util;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class BaseController {


    @Autowired
    protected WholesaleServiceImpl wholesaleService;

    @Autowired
    protected ModelMapper modelMapper;


    protected WholesaleDto convertToDto(Wholesale wholesale) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(wholesale, WholesaleDto.class);
    }


    protected Wholesale convertToEntity(Integer userId, WholesaleDto wholesaleDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Wholesale wholesale = modelMapper.map(wholesaleDto, Wholesale.class);
        wholesale.setSlug(UUID.randomUUID().toString());
        wholesale.setUpdatedAt(Util.getCurrentTime());
        wholesale.setCreatedAt(Util.getCurrentTime());
        wholesale.setCreatedBy(userId);
        wholesale.setUpdatedBy(userId);
        return wholesale;
    }

}
