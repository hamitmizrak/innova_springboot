package com.hamitmizrak.innova_springboot.data.repository;

import com.hamitmizrak.innova_springboot.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<AdressEntity,Long>
// CrudRepository<AdressEntity,Long>
// PagingAndSortingRepository<AdressEntity,Long>

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity,Long> {

    // Delivered Query
    /*
    Spring Data JPA'nın sağladığı otomatik sorgulardır. Yöntem adlarına göre sorgular otomatik olarak üretilir.
    Özellikler:Sorgular otomatik olarak oluşturulur.
    Kod yazımı hızlıdır.

    findByFistname: fistname alanında eşleşen müşteri döner.
findByLastnameStartingWith: Belirli bir harf dizisiyle başlayan lastname değerleri aranır.
findByNotesContaining: notes alanında belirli bir kelime içeren müşteriler bulunur.
     */
    // 1. İlk adı verilen bir müşteriyi bul
    CustomerEntity findByFistname(String firstname);

    // 2. Soyadı belirli bir kelimeyle başlayan müşterileri listele
    List<CustomerEntity> findByLastnameStartingWith(String prefix);

    // 3. Notları belirli bir kelime içeren müşterileri listele
    List<CustomerEntity> findByNotesContaining(String keyword);

    ///////////////////////////////////////////////////////////////
    // Named Query
    /*
    @NamedQuery anotasyonu ile tanımlanan statik sorgulardır.

    Özellikler:
    Sorgular doğrudan entitelerde tanımlanır.
    Tekrarlanabilir ve sabittir.
     */
    // CustomerEntity öncelikle @NamedQueries yazmalısınız
    // 1. Tüm müşterileri getir
    List<CustomerEntity> findAllCustomers();

    // 2. Belirli soyadı olan müşteriyi getir
    List<CustomerEntity> findByLastname(String lastname);

    // 3. Notlarda belirli bir ifadeyi içeren müşterileri getir
    List<CustomerEntity> findWithNotes(String notes);


    ///////////////////////////////////////////////////////////////
    // JPQL
    /*
    Java Persistence Query Language (JPQL) kullanılarak yazılan dinamik sorgulardır.
    Özellikler:
    Entite üzerine çalışır, SQL'den soyutlanmıştır.
    Daha okunaklı ve güvenlidir.
     */

    // import org.springframework.data.jpa.repository.Query;
    // import org.springframework.data.repository.query.Param;

    // 1. Belirli bir soyadı ile müşterileri listele
    @Query("SELECT c FROM Customers c WHERE c.lastname = :lastname")
    List<CustomerEntity> findCustomersByLastname(@Param("lastname") String lastname);

    // 2. Notlara göre arama yap
    @Query("SELECT c FROM Customers c WHERE c.notes LIKE %:keyword%")
    List<CustomerEntity> searchCustomersByNotes(@Param("keyword") String keyword);

    // 3. Adres bilgisine göre müşterileri getir
    // Normal Kullanım
    // @Query("SELECT c FROM Customers c JOIN c.addressCustomerEntity a WHERE a.city = :city")
    // Embeddable oludğu için
    @Query("SELECT c FROM Customers c JOIN c.addressCustomerEntity a WHERE a.addresseEntityDetailsEmbedable.city = :city")
    List<CustomerEntity> findCustomersByCity(@Param("city") String city);


}
