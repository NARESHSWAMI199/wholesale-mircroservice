package com.items.services;


import com.items.dto.ItemsDto;
import com.items.dto.ItemsFilters;
import com.items.entities.Item;
import org.springframework.data.domain.Page;

public interface ItemsService {


    Item findItemBySlug(String slug);

    Item createItem(Item item);

    Item updateItem(ItemsDto itemsDto);

    Page<Item> getAllItems(ItemsFilters itemsFilters);

    void updateStatus(Boolean status,String slug,int userId);

    void updateInStock (Boolean stock,String slug,int userId);

    void deleteItem(String slug);


}
