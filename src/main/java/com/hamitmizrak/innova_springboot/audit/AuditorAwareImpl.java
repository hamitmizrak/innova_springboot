package com.hamitmizrak.innova_springboot.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// LOMBOK
@Log4j2

// StereoType
@Component
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        // Authentication
        // import org.springframework.security.core.Authentication;
        // Login olmuş Kullanıcı bilgisini almak
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Sisteme Login Olmuş Kullanıcı var mı ?
        if(authentication != null && authentication.isAuthenticated()) {
            log.warn("Sistemdeki Kullanıcı Name Bilgisi: "+authentication.getName());
            System.err.println("Sistemdeki Kullanıcı Bilgisi: "+authentication.getName());
            log.warn("Sistemdeki Kullanıcı Bilgisi: "+authentication.getPrincipal().toString());
            return Optional.of(authentication.getName());
        }
        // Eğer sistemde aktif kullanıcı yoksa, default kullanıcı ekle
        return Optional.of("HamitM");
    }
}
