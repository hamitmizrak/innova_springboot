package com.hamitmizrak.innova_springboot.data.repository;

import com.hamitmizrak.innova_springboot.data.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JpaRepository<AdressEntity,Long>
// CrudRepository<AdressEntity,Long>
// PagingAndSortingRepository<AdressEntity,Long>

@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity,Long> {

    // Delivered Query
    Optional<AddressEntity> findAdressEntitiesByAddressDetailsEmbedable_AddressQrCode  (String qrCode);

    // JPQL
    // Native Query
    // Native SQL
}
