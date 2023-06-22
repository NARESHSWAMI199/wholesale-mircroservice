package com.wholesale.services;

import com.wholesale.dto.BasicActions;
import com.wholesale.dto.WholesaleDto;
import com.wholesale.dto.WholesaleFilters;
import com.wholesale.entities.Wholesale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.wholesale.specification.WholesaleSpecification.*;

@Service
public class WholesaleServiceImpl extends BaseService implements WholesaleServices {


    @Override
    public Page<Wholesale> getAllWholesale(WholesaleFilters wholesaleFilters) {

        Specification<Wholesale> specification = Specification.where(
                containsName(wholesaleFilters.getStoreName())
                        .and(hasRating(wholesaleFilters.getRating()))
                        .and(isStatus(wholesaleFilters.getStatus()))
                        .and(isSlug(wholesaleFilters.getSlug()))
                        .and(greaterThanOrEqualFromDate(wholesaleFilters.getFromDate()))
                        .and(lessThanOrEqualToToDate(wholesaleFilters.getToDate()))
        );

        Pageable pageable = PageRequest.of(wholesaleFilters.getStart(), wholesaleFilters.getLength(), Sort.by("id"));
        Page<Wholesale> wholesales = wholesaleRepository.findAll(specification, pageable);
        wholesales.forEach(wholesale ->{
            wholesale.setUser(userService.getUserById(wholesale.getUserId()));
        });
        return wholesales;
    }

    @Override
    public Wholesale getWholesaleById(int id) {
        Wholesale wholesale = wholesaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Wholesale not found."));
        System.out.println(userService.getUserById(1).getUsername());
        wholesale.setUser(userService.getUserById(1));
        return wholesale;
    }

    @Override
    public Wholesale getWholesaleBySlug(String slug) {
        Wholesale wholesale = wholesaleRepository.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Wholesale not found."));
        wholesale.setUser(userService.getUserById(wholesale.getUserId()));
        return wholesale;
    }

    @Override
    public Wholesale getWholesaleByUserId(int userId) {
        return null;
    }

    @Override
    public void deleteWholesale(String slug, int userId) {
        wholesaleRepositoryCriteria.deleteBySlug(slug, userId);
    }

    @Override
    public Wholesale createWholesale(Wholesale wholesale) {
        return wholesaleRepository.save(wholesale);
    }

    @Override
    public void updateStatus(BasicActions basicActions, int userId) {
        wholesaleRepositoryCriteria.updateStatus(basicActions, userId);
    }

    @Override
    public Wholesale updateWholesale(WholesaleDto wholesaleDto, int userId) {
        if (wholesaleDto.getId() > 0) {
            return wholesaleRepositoryCriteria.updateWholesale(wholesaleDto, userId);
        } else {
            return createWholesale(entityMapper.convertToEntity(userId, wholesaleDto));
        }
    }


}
