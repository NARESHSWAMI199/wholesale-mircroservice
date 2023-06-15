package com.auth.respositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class BaseRepository {


    @Autowired
    EntityManager entityManager;


}
