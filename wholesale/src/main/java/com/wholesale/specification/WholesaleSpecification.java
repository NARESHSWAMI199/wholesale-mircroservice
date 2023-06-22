package com.wholesale.specification;


import com.wholesale.entities.Wholesale;
import com.wholesale.entities.Wholesale_;
import org.springframework.data.jpa.domain.Specification;

public class WholesaleSpecification {

    public static Specification<Wholesale> isStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.equal(root.get(Wholesale_.status),status);
        };
    }


    public static Specification<Wholesale> isId(int id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Wholesale_.id),id);
        };
    }

    public static Specification<Wholesale> containsName(String storeName) {
        return (root, query, criteriaBuilder) -> {
            if (storeName == null ) return null;
            return criteriaBuilder.like(root.get(Wholesale_.storeName),"%"+storeName+"%");
        };
    }


    public static Specification<Wholesale> isSlug(String slug) {
        return (root, query, criteriaBuilder) -> {
            if(slug ==null) return null;
            return criteriaBuilder.equal(root.get(Wholesale_.slug),slug);
        };
    }

    public static Specification<Wholesale> hasRating(Float rating) {
        return (root, query, criteriaBuilder) -> {
            if(rating ==null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get(Wholesale_.rating),rating);
        };
    }


    public static Specification<Wholesale> greaterThanOrEqualFromDate(Long fromDate) {
        return (root, query, criteriaBuilder) -> {
            if (fromDate == null) return  null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get(Wholesale_.CREATED_AT), fromDate);
        };
    }

    public static Specification<Wholesale> lessThanOrEqualToToDate(Long toDate) {
        return (root, query, criteriaBuilder) -> {
            if (toDate == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get(Wholesale_.CREATED_AT), toDate);
        };
    }


}
