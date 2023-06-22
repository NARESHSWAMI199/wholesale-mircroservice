package com.items.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsDto {
    Integer id;
    String slug;
    Integer storeId;
    String itemName;
    String image;
    Float price;
    Float discount;
    Boolean status;
    Boolean inStock;
    String itemCategory;
    Float rating;
    String description;
    Long createdAt;
    Long updatedAt;
    Integer createdBy;
    Integer updatedBy;
}
