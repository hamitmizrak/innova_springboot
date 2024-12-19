package com.hamitmizrak.innova_springboot.aspect;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityName;
    private String action;
    private String performedBy;
    private String message; // Log mesajı
    private Date timestamp; // Zaman damgası

    public AuditLogEntity(String entityName, String action, String performedBy, Date timestamp) {
        this.entityName = entityName;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
    }
}