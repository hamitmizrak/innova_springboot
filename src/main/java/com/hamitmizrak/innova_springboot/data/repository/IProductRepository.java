package com.hamitmizrak.innova_springboot.data.repository;

import com.hamitmizrak.innova_springboot.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository<ProductEntity,Long>
// CrudRepository<ProductEntity,Long>
// PagingAndSortingRepository<ProductEntity,Long>

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Long> {

    // Delivered Query
    // Named Query
    // JPQL
    // Native Query
}
