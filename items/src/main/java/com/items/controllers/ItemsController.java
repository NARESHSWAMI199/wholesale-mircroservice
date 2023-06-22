package com.items.controllers;


import com.items.dto.BasicActions;
import com.items.dto.ItemsDto;
import com.items.dto.ItemsFilters;
import com.items.services.ItemsServiceImpl;
import com.items.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("item")
public class ItemsController {

    @Autowired
    ItemsServiceImpl itemsServiceImpl;

    @Autowired
    EntityMapper entityMapper;

    @PostMapping
    public ResponseEntity<Page<ItemsDto>> findAllItems(@RequestBody ItemsFilters itemsFilters) {
        Page<ItemsDto> allItems = itemsServiceImpl.getAllItems(itemsFilters).map(item -> entityMapper.convertToDto(item));
        return ResponseEntity.ok().body(allItems);
    }



    @PostMapping(value = {"create", "update"})
    public ResponseEntity<Map<String, Object>> updateItem(@RequestBody ItemsDto itemsDto) {
        ItemsDto item = entityMapper.convertToDto(itemsServiceImpl.updateItem(itemsDto));
        Map<String, Object> response = new HashMap<>();
        response.put("res", item);
        if (itemsDto.getId() < 1) {
            response.put("message", "New item successfully added.");
            return ResponseEntity.status(200).body(response);
        }
        response.put("message", "Item successfully updated.");
        return ResponseEntity.status(201).body(response);
    }


    @PostMapping("/updateStatus")
    public ResponseEntity<Map<String, Object>> updateStatus(@RequestBody BasicActions basicActions) {
        itemsServiceImpl.updateStatus(basicActions.getStatus(),basicActions.getSlug(),0);
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Item status updated successfully.");
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/updateStock")
    public ResponseEntity updateStock(@RequestBody BasicActions basicActions) {
        itemsServiceImpl.updateInStock(basicActions.getStatus(),basicActions.getSlug(),0);
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Stock updated successfully.");
        return ResponseEntity.status(201).body(response);
    }



    @GetMapping("/delete/{slug}")
    public  ResponseEntity<Map<String, Object>> deleteItem(@PathVariable String slug) {
        itemsServiceImpl.deleteItem(slug);
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Item deleted successfully.");
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{slug}")
    public  ResponseEntity<ItemsDto> getItemBySlug(@PathVariable String slug) {
        ItemsDto itemsDto = entityMapper.convertToDto(itemsServiceImpl.findItemBySlug(slug));
        return ResponseEntity.ok().body(itemsDto);
    }

}
