package com.auth.respositories;


import com.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserRepositoryClass  extends  BaseRepository{



    /** there we will criteria builder */
    public Optional<User> getUserByUserByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("email"),email));
        Query query = entityManager.createQuery(cr);
        return (Optional<User>) query.getSingleResult();
    }








}
