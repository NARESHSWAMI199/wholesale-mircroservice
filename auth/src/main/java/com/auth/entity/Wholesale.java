package com.auth.entity;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wholesale {

    Integer userId;
    String storeName;
    String image;
    String storeCategory;
    Float rating;
    String description;
    Integer addressId;
    Boolean status = Boolean.TRUE;
    Long createdAt;
    Long updatedAt;


}