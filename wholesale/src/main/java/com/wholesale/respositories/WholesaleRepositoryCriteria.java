package com.wholesale.respositories;


import com.wholesale.dto.BasicActions;
import com.wholesale.dto.WholesaleDto;
import com.wholesale.entities.Wholesale;
import com.wholesale.entities.Wholesale_;
import com.wholesale.respositories.WholesaleRepository;
import com.wholesale.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Repository
public class WholesaleRepositoryCriteria {


    @Autowired
    private WholesaleRepository wholesaleRepository;
    @Autowired
    EntityManager entityManager;



    @Transactional
    public Wholesale updateWholesale(WholesaleDto wholesaleDto, int updatedBy){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Wholesale> wholesaleUpdate = criteriaBuilder.createCriteriaUpdate(Wholesale.class);
        Root<Wholesale> root = wholesaleUpdate.from(Wholesale.class);

        wholesaleUpdate
                .set(Wholesale_.storeName, wholesaleDto.getStoreName())
                .set(Wholesale_.image, wholesaleDto.getImage())
                .set(Wholesale_.description, wholesaleDto.getDescription())
                .set(Wholesale_.updatedAt, Util.getCurrentTime())
                .set(Wholesale_.updatedBy,updatedBy)
                .where(criteriaBuilder.equal(root.get(Wholesale_.slug),wholesaleDto.getSlug()));
        entityManager.createQuery(wholesaleUpdate).executeUpdate();
        return wholesaleRepository.findById(wholesaleDto.getId()).orElseThrow(()-> new EntityNotFoundException("Any Store not found to update."));
    }



    @Transactional
    public void updateStatus(BasicActions basicActions,int updatedBy){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Wholesale> wholesaleUpdate = criteriaBuilder.createCriteriaUpdate(Wholesale.class);
        Root<Wholesale> root = wholesaleUpdate.from(Wholesale.class);

        wholesaleUpdate
                .set(Wholesale_.status, basicActions.getStatus())
                .set(Wholesale_.updatedAt, Util.getCurrentTime())
                .set(Wholesale_.updatedBy,updatedBy)
                .where(criteriaBuilder.equal(root.get(Wholesale_.slug),basicActions.getSlug()));
        entityManager.createQuery(wholesaleUpdate).executeUpdate();
    }



    @Transactional
    public void deleteBySlug(String slug,int updatedBy){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Wholesale> wholesaleUpdate = criteriaBuilder.createCriteriaUpdate(Wholesale.class);
        Root<Wholesale> root = wholesaleUpdate.from(Wholesale.class);

        wholesaleUpdate
                .set(Wholesale_.isDeleted, true)
                .set(Wholesale_.updatedAt, Util.getCurrentTime())
                .set(Wholesale_.updatedBy,updatedBy)
                .where(criteriaBuilder.equal(root.get(Wholesale_.slug),slug));
        entityManager.createQuery(wholesaleUpdate).executeUpdate();
    }




}
