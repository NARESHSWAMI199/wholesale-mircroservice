package com.wholesale.services;


import com.wholesale.dto.BasicActions;
import com.wholesale.dto.WholesaleDto;
import com.wholesale.dto.WholesaleFilters;
import com.wholesale.entities.Wholesale;
import org.springframework.data.domain.Page;

public interface WholesaleServices {


    Page<Wholesale> getAllWholesale(WholesaleFilters wholesaleFilters);

    Wholesale getWholesaleById(int id);

    Wholesale getWholesaleBySlug(String slug);

    Wholesale getWholesaleByUserId(int userId);

    void deleteWholesale(String slug,int userId);


    Wholesale createWholesale(Wholesale wholesale);

    void updateStatus(BasicActions basicActions, int userId);


    Wholesale updateWholesale(WholesaleDto wholesaleDto,int userId);

}
