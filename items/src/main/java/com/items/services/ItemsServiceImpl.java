package com.items.services;


import com.items.dto.ItemsDto;
import com.items.dto.ItemsFilters;
import com.items.entities.Item;
import com.items.repositories.ItemsRepository;
import com.items.repositories.ItemsRepositoryCriteria;
import com.items.utils.EntityMapper;
import com.items.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.items.specifications.ItemsSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    ItemsRepositoryCriteria itemsRepositoryCriteria;

    @Override
    public Item findItemBySlug(String slug) {
        return itemsRepository.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

    @Override
    public Item createItem(Item item) {
        return itemsRepository.save(item);
    }


    @Override
    @Transactional
    public Item updateItem(ItemsDto itemsDto) {
        if (itemsDto.getId() > 1){
            itemsDto.setUpdatedAt(Util.getCurrentTime());
            itemsDto.setUpdatedBy(0);
            return itemsRepositoryCriteria.updateItem(itemsDto);
        }
        return createItem(entityMapper.convertToEntity(0, itemsDto));
    }


    @Override
    public Page<Item> getAllItems(ItemsFilters itemsFilters) {

        Specification<Item> specification = where(containsName(
                itemsFilters.getItemName())
                .and(hasCategory(itemsFilters.getCategory()))
                .and(isStatus(itemsFilters.getStatus()))
                .and(greaterThanOrEqualFromDate(itemsFilters.getFromDate()))
                .and(lessThanOrEqualToToDate(itemsFilters.getToDate()))
                .and(inStock(itemsFilters.getInStock()))
                .and(hasSlug(itemsFilters.getKey()))
                .and(isWholesaleId(itemsFilters.getStoreId()))
        );
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").descending());
        return itemsRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public void updateStatus(Boolean status,String slug,int userId) {
        itemsRepositoryCriteria.updateStatus(status,slug,userId);
    }

    @Override
    @Transactional
    public void updateInStock(Boolean stock, String slug,int userId) {
        itemsRepositoryCriteria.updateStock(stock,slug,userId);
    }

    @Override
    @Transactional
    public void deleteItem(String slug) {
        itemsRepository.deleteBySlug(slug);
    }
}
