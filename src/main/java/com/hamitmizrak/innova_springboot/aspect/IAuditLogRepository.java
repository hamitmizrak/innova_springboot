package com.hamitmizrak.innova_springboot.aspect;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditLogRepository extends JpaRepository<AuditLogEntity, Long> {
}
