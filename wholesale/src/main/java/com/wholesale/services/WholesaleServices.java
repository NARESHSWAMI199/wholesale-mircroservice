package com.wholesale.services;


import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;

import java.util.List;

public interface WholesaleServices {


    List<Wholesale> getAllWholesale();
    public Wholesale getWholesaleById(int id);

    public Wholesale getWholesaleByUserId(int userId);

    public Wholesale deleteWholesale(int id);

    public Wholesale createWholesale(Wholesale wholesale);

}
