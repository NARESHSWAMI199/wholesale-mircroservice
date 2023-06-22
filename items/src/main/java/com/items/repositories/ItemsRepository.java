package com.items.repositories;


import com.items.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer>, PagingAndSortingRepository<Item, Integer>,
        JpaSpecificationExecutor<Item> {

    /** custom function */
    Optional<Item> findBySlug(String slug);




    @Modifying
    @Query("update Item i set i.isDeleted=true where i.slug=:slug")
    void deleteBySlug(@Param("slug") String slug);


}
