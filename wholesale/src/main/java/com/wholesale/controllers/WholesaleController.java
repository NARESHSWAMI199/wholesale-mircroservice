package com.wholesale.controllers;


import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wholesale")
public class WholesaleController extends BaseController {


    @GetMapping("/")
    public ResponseEntity<List<WholesaleDto>> getAllWholesale() {
        List<WholesaleDto> wholesales = wholesaleService.getAllWholesale().stream().map(wholesale -> this.convertToDto(wholesale)).collect(Collectors.toList());
        return ResponseEntity.ok().body(wholesales);
    }


    @GetMapping("/{id}")
    public ResponseEntity<WholesaleDto> getWholesaleById(@PathVariable Integer id) {
        WholesaleDto wholesaleDto = convertToDto(wholesaleService.getWholesaleById(id));
        return ResponseEntity.ok().body(wholesaleDto);
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<Map<String, Object>> deleteWholesale(@PathVariable Integer id) {
        wholesaleService.deleteWholesale(id);
        Map<String, Object> response = new HashMap();
        response.put("message", "Successfully deleted.");
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody WholesaleDto wholesaleDto) {
        Map<String, Object> response = new HashMap();
        Wholesale wholesale = convertToEntity(0, wholesaleDto);
        WholesaleDto wholesaleDt = convertToDto(wholesaleService.createWholesale(wholesale));
        response.put("message", "Successfully updated.");
        response.put("res", wholesaleDt);
        return ResponseEntity.ok().body(response);
    }



/*
    @GetMapping("/create")
    public ResponseEntity<WholesaleDto> createWholesale(WholesaleDto wholesaleDto) {
        WholesaleDto wholesale = wholesaleService.createWholesale(WholesaleDto);
        Map<String, Object> response = new HashMap();
        response.put("message", "Successfully created.");
        response.put("res", )
        return ResponseEntity.ok().body(response);
    }
*/


}
