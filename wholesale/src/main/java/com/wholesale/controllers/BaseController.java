package com.wholesale.controllers;

import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;
import com.wholesale.services.WholesaleServiceImpl;
import com.wholesale.utils.Util;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class BaseController {


    @Autowired
    protected WholesaleServiceImpl wholesaleService;


}
