package com.wholesale.controllers;


import com.wholesale.dto.BasicActions;
import com.wholesale.dto.WholesaleDto;
import com.wholesale.dto.WholesaleFilters;
import com.wholesale.utils.EntityMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wholesale")
public class WholesaleController extends BaseController {


  @Autowired
  private EntityMapper entityMapper;

    @PostMapping("")
    public ResponseEntity<Page<WholesaleDto>> getAllWholesale(@RequestBody WholesaleFilters wholesaleFilters) {
        Page<WholesaleDto> wholesales = wholesaleService.getAllWholesale(wholesaleFilters).map(wholesale -> entityMapper.convertToDto(wholesale));
        return ResponseEntity.ok().body(wholesales);
    }


    @GetMapping("/{slug}")
    @CircuitBreaker(name="hoaldetail",fallbackMethod = "hello")
    public ResponseEntity<WholesaleDto> getWholesaleById(@PathVariable String slug) {
        WholesaleDto wholesaleDto = entityMapper.convertToDto(wholesaleService.getWholesaleBySlug(slug));
        return ResponseEntity.ok().body(wholesaleDto);
    }

    public ResponseEntity<WholesaleDto> hello(String slug ,Exception e) {
        e.printStackTrace();
        WholesaleDto wholesaleDto = WholesaleDto.builder().storeName("dummy").build();
        return ResponseEntity.ok().body(wholesaleDto);
    }
    @GetMapping("/delete/{slug}")
    public ResponseEntity<Map<String, Object>> deleteWholesale(@PathVariable String slug) {
        wholesaleService.deleteWholesale(slug,0);
        Map<String, Object> response = new HashMap();
        response.put("message", "Successfully deleted.");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/updateStatus")
    public ResponseEntity<Map<String, Object>> deleteWholesale(@RequestBody BasicActions basicActions) {
        wholesaleService.updateStatus(basicActions,0);
        Map<String, Object> response = new HashMap();
        response.put("message", "Successfully deleted.");
        return ResponseEntity.ok().body(response);
    }


    @PostMapping(value = {"update","create"})
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody WholesaleDto wholesaleDto) {
        Map<String, Object> response = new HashMap();
        WholesaleDto wholesaleDt = entityMapper.convertToDto(wholesaleService.updateWholesale(wholesaleDto,0));
        response.put("res", wholesaleDt);
        if (wholesaleDto.getId() > 1) {
            response.put("message", "Successfully updated.");
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.status(201).body(response);
    }


}
