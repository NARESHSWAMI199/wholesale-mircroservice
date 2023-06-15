package com.wholesale.services;

import com.wholesale.entities.Wholesale;
import com.wholesale.external.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class WholesaleServiceImpl extends BaseService implements WholesaleServices {


    @Override
    public List<Wholesale> getAllWholesale() {
        List<Wholesale> wholesales = wholesaleRepository.findAll();
        return wholesales;
    }

    @Override
    public Wholesale getWholesaleById(int id) {
        Wholesale wholesale = wholesaleRepository.findById(id).orElseThrow(() -> new NoResultException());
        System.out.println(userService.getUserById(1).getUsername());
        wholesale.setUser(userService.getUserById(1));
        return wholesale;
    }

    @Override
    public Wholesale getWholesaleByUserId(int userId) {
        return null;
    }

    @Override
    public Wholesale deleteWholesale(int id) {
        return null;
    }

    @Override
    public Wholesale createWholesale(Wholesale wholesale) {
        return wholesaleRepository.save(wholesale);
    }


}
