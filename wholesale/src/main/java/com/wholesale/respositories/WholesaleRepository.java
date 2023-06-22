package com.wholesale.respositories;


import com.wholesale.entities.Wholesale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WholesaleRepository extends JpaRepository<Wholesale,Integer>, PagingAndSortingRepository<Wholesale,Integer>, JpaSpecificationExecutor<Wholesale> {


    /** custom methods */

    Optional<Wholesale> findBySlug(String slug);

    @Query("Update Wholesale w set w.isDeleted=true where w.slug = :slug")
    void deleteBySlug(@Param("slug") String slug);
}
