package com.auth.external.services;


import com.auth.entity.Wholesale;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "WHOLESALE-SERVICE")
public interface WholesaleService {


    @GetMapping("/wholesale/{id}")
    Wholesale getWholesaleById(Integer id);
}
