package com.wholesale.dto;


import com.wholesale.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WholesaleDto {
    int id;
    String storeName;
    String slug;
    String image;
    String storeCategory;
    Float rating;
    String description;
    Integer addressId;
    Boolean status = Boolean.TRUE;
    Long createdAt;
    Long updatedAt;
    User user;


}
