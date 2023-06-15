package com.auth.services;


import com.auth.external.services.WholesaleService;
import com.auth.respositories.UserRepository;
import com.auth.respositories.UserRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserRepositoryClass userRepositoryClass;


    @Autowired
    protected  WholesaleService wholesaleService;

}
