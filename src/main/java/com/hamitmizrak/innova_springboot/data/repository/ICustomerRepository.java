package com.hamitmizrak.innova_springboot.data.repository;

import com.hamitmizrak.innova_springboot.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<AdressEntity,Long>
// CrudRepository<AdressEntity,Long>
// PagingAndSortingRepository<AdressEntity,Long>

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity,Long> {

    // Delivered Query
    // Named Query
    // JPQL
    // Native Query

}
