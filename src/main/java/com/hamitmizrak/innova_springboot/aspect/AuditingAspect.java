package com.hamitmizrak.innova_springboot.aspect;

import com.hamitmizrak.innova_springboot.login.AuthenticationName;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Aspect
@Component
public class AuditingAspect {

    @Autowired
    private IAuditLogRepository iAuditLogRepository;

    // AuditingAspect'e ek olarak bir toplu kayıt yöntemi ekleyin:
    public void logBatchAudit(List<AuditLogEntity> auditLogs) {
        iAuditLogRepository.saveAll(auditLogs);
    }

    @AfterReturning("execution(* com.hamitmizrak.innova_springboot.business.services.impl.*.*(..))")
    public void logAudit(JoinPoint joinPoint) {
        // İşlem yapılan sınıf adı
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // Metod adı
        String methodName = joinPoint.getSignature().getName();

        // Varsayılan kullanıcı
        String performedBy = AuthenticationName.authenticationName()!=null
                ? AuthenticationName.authenticationName()
                : "Hamit Mızrak";

        // Yeni bir AuditLog kaydı oluştur
        AuditLogEntity log = new AuditLogEntity(
                className,
                methodName,
                performedBy,
                new Date()
        );

        // Veritabanına kaydet
        iAuditLogRepository.save(log);

        System.out.println("Audit Log Created: " + log);
    }
}


/*
Açıklamalar
Aspect Tanımı: AuditingAspect sınıfı, tüm servis katmanındaki metot çağrılarını @AfterReturning ile dinler ve işlem sonrası log kaydı oluşturur.
Dinleme Noktaları: execution(* com.example.auditing.service.*.*(..)) ifadesi, servis katmanındaki tüm sınıfların tüm metotlarını kapsar.
Audit Log: AuditLog sınıfı ile loglama işlemleri H2 veritabanında saklanır.
Performans Etkisi: AOP'nin loglama amacıyla kullanımı sistem performansına minimum etki yapar,
çünkü işlemler yalnızca metodun sonunda tetiklenir.
Bu örnek, Spring AOP kullanarak audit işlemleri için temel bir yapı sağlar.
Sistemi genişletmek için loglama detaylarını artırabilir, kullanıcı kimlik doğrulama bilgilerini dinamik olarak ekleyebilir ve farklı olaylar için özelleştirilmiş loglama gerçekleştirebilirsiniz.
 */