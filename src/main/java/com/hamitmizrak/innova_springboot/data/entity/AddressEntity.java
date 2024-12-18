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
        catalog = "blog",
        uniqueConstraints = @UniqueConstraint(columnNames = {"state"}),
        indexes = {
                @Index(name="idx_address_city",columnList = "city",unique = true)
        }
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
    @Version
    private int version;

    // RELATION
}
