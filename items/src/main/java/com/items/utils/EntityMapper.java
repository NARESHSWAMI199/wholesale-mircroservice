package com.items.utils;

import com.items.dto.ItemsDto;
import com.items.entities.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.items.utils.Util.getCurrentTime;

@Component
public class EntityMapper {


    @Autowired
    ModelMapper modelMapper;

    public  ItemsDto convertToDto(Item item) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(item, ItemsDto.class);
    }


    public  Item convertToEntity(Integer userId, ItemsDto itemsDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Item item = modelMapper.map(itemsDto, Item.class);
        item.setSlug(UUID.randomUUID().toString());
        item.setUpdatedAt(getCurrentTime());
        item.setCreatedAt(getCurrentTime());
        item.setCreatedBy(userId);
        item.setUpdatedBy(userId);
        return item;
    }


}
