package com.hamitmizrak.innova_springboot.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// LOMBOK
@Log4j2

public class AuthenticationName {

    // Eğer kullanıcı varsa
    public static String authenticationName() {
        // Authentication
        // import org.springframework.security.core.Authentication;
        // Login olmuş Kullanıcı bilgisini almak
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Sisteme Login Olmuş Kullanıcı var mı ?
        if (authentication != null && authentication.isAuthenticated()) {
            log.warn("Sistemdeki Kullanıcı Name Bilgisi: " + authentication.getName());
            System.err.println("Sistemdeki Kullanıcı Bilgisi: " + authentication.getName());
            log.warn("Sistemdeki Kullanıcı Bilgisi: " + authentication.getPrincipal().toString());
            return authentication.getName();
        }
       return "Hamit Mızrak";
    }

}
