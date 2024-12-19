package com.hamitmizrak.innova_springboot.security.jwt;
// Bu sınıfın com.hamitmizrak.innova_springboot.security.jwt paketi altında yer aldığını belirtir.

import lombok.RequiredArgsConstructor;
// Lombok kütüphanesinden @RequiredArgsConstructor, final değişkenler için otomatik olarak constructor oluşturur.

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// Spring Security tarafından kimlik doğrulama için kullanılan bir token sınıfı.

import org.springframework.security.core.context.SecurityContextHolder;
// Uygulama genelinde güvenlik bilgilerini tutar. Örneğin, kullanıcı oturum bilgileri.

import org.springframework.security.core.userdetails.UserDetails;
// Kullanıcı detaylarını tutan bir arayüz. Genellikle kullanıcı bilgilerini temsil eder.

import org.springframework.security.core.userdetails.UserDetailsService;
// Kullanıcı detaylarını yüklemek için kullanılan bir servis arayüzü.

import org.springframework.stereotype.Component;
// Spring bileşeni olarak bu sınıfın kullanılmasını sağlar.

import org.springframework.web.filter.OncePerRequestFilter;
// Her HTTP isteği için bir kez çalışacak olan bir filtre sınıfı.

import java.io.IOException;
// Giriş/çıkış işlemleri sırasında ortaya çıkabilecek istisnaları işlemek için kullanılır.

// LOMBOK
@RequiredArgsConstructor
// Lombok tarafından sağlanan bu notasyon, final olarak tanımlanmış değişkenler için bir constructor oluşturur.

@Component
// Bu sınıfı bir Spring bileşeni olarak işaretler. IOC konteynerine dahil edilir ve diğer bileşenlerde kullanılabilir.
public class _9_JwtFilter extends OncePerRequestFilter {

    // Injection
    private final _8_JwtUtils jwtUtils;
    // JWT işlemleri için kullanılan yardımcı sınıf. Token oluşturma ve doğrulama işlevlerini sağlar.

    private final UserDetailsService userDetailsService;
    // Kullanıcı bilgilerini yüklemek için kullanılan Spring Security bileşeni.

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        // HTTP istekleri için özel filtre mantığını uygular.

        String header = request.getHeader("Authorization");
        // HTTP isteğinin "Authorization" başlığını alır.

        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {
            // Eğer "Authorization" başlığı mevcutsa ve "Bearer " ile başlıyorsa:
            token = header.substring(7);
            // "Bearer " ifadesinden sonraki token kısmını alır.
            username = jwtUtils.getUsernameFromJwtToken(token);
            // Token içerisinden kullanıcı adını ayrıştırır.
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Eğer username varsa ve mevcut oturumda kimlik doğrulama bilgisi yoksa:
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Kullanıcı detaylarını yükler.

            if (jwtUtils.validateJwtToken(token)) {
                // Eğer token geçerliyse:
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()));
                // Kullanıcı kimlik doğrulama bilgilerini Spring Security'ye ayarlar.
            }
        }

        filterChain.doFilter(request, response);
        // İsteği bir sonraki filtreye veya hedefe iletir.
    }
}
