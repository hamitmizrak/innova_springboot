package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.audit.AuditingAwareBaseDto;
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
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    // SYSTEM DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="system_created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date systemCreatedDate;
}
