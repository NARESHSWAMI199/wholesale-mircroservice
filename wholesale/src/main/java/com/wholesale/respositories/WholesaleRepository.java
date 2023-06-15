package com.wholesale.respositories;


import com.wholesale.entities.Wholesale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleRepository extends JpaRepository<Wholesale,Integer> {



}
