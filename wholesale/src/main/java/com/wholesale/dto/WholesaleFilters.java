package com.wholesale.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WholesaleFilters {

    int start = 0;
    int length = 10;

    String slug;
    Float rating;
    Long fromDate;
    Long toDate;
    String storeName;
    Boolean status;

}
