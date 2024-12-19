package com.hamitmizrak.innova_springboot.data.repository;

import com.hamitmizrak.innova_springboot.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<OrderEntity,Long>
// CrudRepository<OrderEntity,Long>
// PagingAndSortingRepository<OrderEntity,Long>

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {

    // Delivered Query
    // Named Query
    // JPQL
    // Native Query
}
