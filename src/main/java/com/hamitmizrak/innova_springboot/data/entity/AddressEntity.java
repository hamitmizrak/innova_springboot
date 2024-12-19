package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.data.embedded.AddresseEntityDetailsEmbedable;
import jakarta.persistence.*;
import lombok.*;

// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

/*
name = "addreseses" => Databesdeki tablo adı
schema = "public"   => Postgresql vb yapılarında şema yapısını destekleten veri tablarında tabloya erişim için kullanılır.
SELECT * FROM public.addreseses
catalog = "blog"    => Mysql vb gibi veritabanlarında
uniqueConstraints = @UniqueConstraint(columnNames = {"state"}) => benzersiz sutun verisi için

indexes = {
                @Index(name="idx_address_city",columnList = "city",unique = true)
        }
        columnList = "city": Indeksin uygulanacağı sütün adıdır. birden fazla sutun eklemek için virgül yazılır
        unique = true: Indeksin benzersiz olmasını sağlar
*/

// Entity
@Entity(name = "Addreses")
@Table(
        name = "addreseses",
        schema = "public",
        catalog = "blog"
        //uniqueConstraints = @UniqueConstraint(columnNames = {"state"}),
        //indexes = {
        //        @Index(name="idx_address_city",columnList = "city",unique = true)
        //}
)
// Address
public class AddressEntity extends BaseEntity{

    // FIELD

    // Embedded
    // Dikkat: Sorting için burada yazan: addresseEntityDetailsEmbedable göre işlem yapılır
    @Embedded
    private AddresseEntityDetailsEmbedable addresseEntityDetailsEmbedable;

    // Database tablo oalrak eklenmesin: sadece Java olsun ancak database sutun adı olması
    @Transient
    private String temporaryData;

    // Optimistik Kilitlenme (Optimistic Locking)
    // Entity'timizin versiyonlararak saklanması
    /*
    Transaction çok uzun süre zarfında açık kalmışsa bu noktada, Version ile çözmeyere çalışırız.
    Optimistic Locking: Veri tabanında kaydın güncellenmesi sırasında veri tutarlılığını sağlamak istiyorsak
     buradan @Version kullanırız. Eğer bu kayıdın işlem başlandı ve eşlenme devam etmiyorsa güncelleme reddecek ve bir hata
     fırlatacak: OptimistikcLockException
     Çözüm olarak: Optimistic Locking kullanarak temel mekanizmamızda Entity üzerinde yazdığımız @Version alanında tanımlananan,
     güncelleme işlemlerinde her zaman değeri otomatik artırım sağlarak çözümlenir.

     Select * From Addresses Where id=1;
     update Addresses SET city="Malatya", version=version+1 where id=1 AND version =1;
    */
    @Version
    private int version;

    // RELATION
}
