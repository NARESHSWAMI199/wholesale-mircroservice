package com.wholesale.services;


import com.wholesale.external.service.UserService;
import com.wholesale.respositories.WholesaleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {


    @Autowired
    protected  WholesaleRepository wholesaleRepository;


    @Autowired
    protected UserService userService;

}
