package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter

@MappedSuperclass
abstract public class BaseEntity extends AuditingAwareBaseEntity implements Serializable {

    // SERILEŞTIRME
    public static final Long serialVersionUID = 456546546546456412L;

    // FIELD
    /*
    GeneratedValue => Birincil Anahtar(PK) nasıl üretileceğini belirtmek için kullanırız.
    GeneratedValue:
    AUTO: Varsayılan Stratejidir. Örnek: Postgresql
    IDENTITY: Veritabanlarında otomatik artırım (AI) içindir.  Örnek: Mysql, H2DB
    SEQUNCE: Sıra kullanmak. Örnek: Oracle, Mysql, Postgresql
    TABLE: Tablo kullanmak içindir
     */

    // 1.YOL
    // Neden Sequence kullanıyoruz ?
    /*
    Performans: Diziler diğer yöntemler olan IDENTITY, AUTO vb yapılara göre daha performansı
    Taşınabilirlik: JPA'in birden fazla Mysql, Postgresql, Oracle, MariaDB, H2DB vb
    Kontrol: allocationSize başlangıç değer artışını kontrol edebilmek
    */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name="address_seq",sequenceName = "address_sequence",allocationSize=1)
    protected Long id;

    // 2.YOL
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql
    @GeneratedValue(strategy = GenerationType.AUTO) //Postgresql
    protected Long id;
    */

    // SYSTEM DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="system_created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date systemCreatedDate;
}
