package com.wholesale.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "stores")
@SQLDelete(sql = "update stores s set s.is_deleted=true where s.id = ?")
@Where(clause = "is_deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wholesale implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "slug")
    String slug;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "store_name")
    String storeName;

    @Column(name = "image")
    String image;

    @Column(name = "store_category")
    String storeCategory;

    @Column(name = "rating")
    Float rating;

    @Column(name = "description")
    String description;

    @Column(name = "address_id")
    Integer addressId;

    @Column(name = "status")
    Boolean status = Boolean.TRUE;

    @Column(name = "created_at")
    Long createdAt;

    @Column(name = "updated_at")
    Long updatedAt;

    @Column(name = "created_by")
    Integer createdBy;

    @Column(name = "updated_by")
    Integer updatedBy;

    @Column(name = "is_deleted")
    boolean isDeleted = Boolean.FALSE;


    @Transient
    User user;

/*
    enum storeCategory {
        Grocery,
        Cloths,
        Electronic,
        Other
    }*/


}
