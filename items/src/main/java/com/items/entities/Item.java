package com.items.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "items")


@SQLDelete(sql="update items i set i.is_deleted = true where i.slug=?")
@Where(clause = "is_deleted=false")



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "slug")
    String slug;
    @Column(name = "store_id")
    Integer storeId;
    @Column(name = "name")
    String itemName;
    @Column(name = "image")
    String image;
    @Column(name = "price")
    Float price;
    @Column(name = "discount")
    Float discount;
    @Column(name = "status")
    Boolean status = Boolean.FALSE;
    @Column(name = "in_stock")
    Boolean inStock;
    @Column(name = "item_category")
    String itemCategory;
    @Column(name = "rating")
    Float rating;
    @Column(name = "description")
    String description;
    @Column(name = "created_at")
    Long createdAt;
    @Column(name = "updated_at")
    Long updatedAt;
    @Column(name = "created_by")
    Integer createdBy;
    @Column(name = "updated_by")
    Integer updatedBy;
    @Column(name = "is_deleted")
    Boolean isDeleted = Boolean.FALSE;
}
