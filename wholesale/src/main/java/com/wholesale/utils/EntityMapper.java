package com.wholesale.utils;

import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class EntityMapper {

    @Autowired
    protected ModelMapper modelMapper;


    public WholesaleDto convertToDto(Wholesale wholesale) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(wholesale, WholesaleDto.class);
    }


    public Wholesale convertToEntity(Integer userId, WholesaleDto wholesaleDto) {
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
