package com.items.repositories;


import com.items.dto.ItemsDto;
import com.items.entities.Item;
import com.items.entities.Item_;
import com.items.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Repository
public class ItemsRepositoryCriteria {


    @Autowired
    EntityManager entityManager;
    @Autowired
    private ItemsRepository itemsRepository;


    public Item updateItem(ItemsDto itemsDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Item.class);
        Root itemRoot = criteriaUpdate.from(Item.class);
        criteriaUpdate
                .set(Item_.image, itemsDto.getImage())
                .set(Item_.itemName, itemsDto.getItemName())
                .set(Item_.price, itemsDto.getPrice())
                .set(Item_.discount, itemsDto.getDiscount())
                .set(Item_.updatedAt, itemsDto.getUpdatedAt())
                .set(Item_.itemCategory, itemsDto.getItemCategory())
                .set(Item_.description, itemsDto.getDescription())
                .set(Item_.updatedBy, itemsDto.getUpdatedBy())
                .where(criteriaBuilder.equal(itemRoot.get(Item_.slug), itemsDto.getSlug()));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
        return itemsRepository.findById(itemsDto.getId()).orElseThrow(() -> new EntityNotFoundException("No entity found to update."));
    }


    public void updateStatus(Boolean status, String slug, int userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Item.class);
        Root itemRoot = criteriaUpdate.from(Item.class);
        criteriaUpdate.set(Item_.STATUS, status)
                .set(Item_.updatedBy, userId)
                .set(Item_.updatedAt, Util.getCurrentTime())
                .where(criteriaBuilder.equal(itemRoot.get(Item_.slug), slug));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


    public void updateStock(Boolean status, String slug, int userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Item.class);
        Root itemRoot = criteriaUpdate.from(Item.class);
        criteriaUpdate.set(Item_.STATUS, status)
                .set(Item_.updatedBy, userId)
                .set(Item_.updatedAt, Util.getCurrentTime()).
                where(criteriaBuilder.equal(itemRoot.get(Item_.slug), slug));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


}
