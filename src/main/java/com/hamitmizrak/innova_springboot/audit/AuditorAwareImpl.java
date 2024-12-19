package com.hamitmizrak.innova_springboot.audit;

import com.hamitmizrak.innova_springboot.login.AuthenticationName;
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

        if(AuthenticationName.authenticationName()!=null){
            return Optional.of(AuthenticationName.authenticationName());
        }
        // Eğer sistemde aktif kullanıcı yoksa, default kullanıcı ekle
        return Optional.of(AuthenticationName.authenticationName());
    }
}
