package com.items.specifications;


import com.items.entities.Item;
import com.items.entities.Item_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ItemsSpecifications {

    /**
     * there we create some predicates which is use for filters
     */


    public static Specification<Item> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null) return null;
            return criteriaBuilder.equal(root.get(Item_.ITEM_CATEGORY), category);
        };
    }

    public static Specification<Item> isWholesaleId(Integer wholesaleId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Item_.storeId), wholesaleId);
        };
    }


    public static Specification<Item> containsName(String itemName) {
        return (root, query, criteriaBuilder) -> {
            if (itemName == null) return null;
            return criteriaBuilder.like(root.get(Item_.ITEM_NAME), "%" + itemName + "%");
        };
    }

    public static Specification<Item> greaterThanOrEqualFromDate(Long fromDate) {
        return (root, query, criteriaBuilder) -> {
            if (fromDate == null) return  null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Item_.CREATED_AT), fromDate);
        };
    }

    public static Specification<Item> lessThanOrEqualToToDate(Long toDate) {
        return (root, query, criteriaBuilder) -> {
            if (toDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get(Item_.CREATED_AT), toDate);
        };
    }


    public static Specification<Item> isStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.equal(root.get(Item_.STATUS),status);
        };
    }



    public static Specification<Item> inStock(Boolean inStock) {
        return (root, query, criteriaBuilder) -> {
            if (inStock == null) return null;
            return criteriaBuilder.equal(root.get(Item_.IN_STOCK),inStock);
        };
    }

    public static Specification<Item> equalOrLessThanPrice(Float price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get(Item_.price), price);
        };
    }



    public static Specification<Item> hasSlug(String slug) {
        return (root, query, criteriaBuilder) -> {
            if (slug == null) return null;
            return criteriaBuilder.equal(root.get(Item_.slug), slug);
        };
    }

}
