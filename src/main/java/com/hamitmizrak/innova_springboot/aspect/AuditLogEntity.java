package com.hamitmizrak.innova_springboot.aspect;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Entity
@Entity(name = "AuditLogs")
@Table(name = "audit_logs")
public class AuditLogEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String entityName;
    private String action;
    private String performedBy;
    private Date timestamp;

    public AuditLogEntity(String entityName, String action, String performedBy, Date timestamp) {
        this.entityName = entityName;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }
}