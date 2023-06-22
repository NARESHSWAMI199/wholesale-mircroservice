package com.items.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemsFilters {


   int start =0;
   int length = 10;
   Integer storeId;
   String key;
   String itemName;
   String price;
   Boolean inStock;
   Boolean status;
   Long fromDate;
   Long toDate;
   String category;

}
