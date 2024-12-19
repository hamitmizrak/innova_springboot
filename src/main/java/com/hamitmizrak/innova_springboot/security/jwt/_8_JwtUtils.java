package com.hamitmizrak.innova_springboot.security.jwt;
// Bu sınıfın com.hamitmizrak.innova_springboot.security.jwt paketi altında yer aldığını belirtir.

import io.jsonwebtoken.*;
// JWT (JSON Web Token) oluşturma, doğrulama ve ayrıştırma işlemleri için gerekli kütüphaneler.

import org.springframework.stereotype.Component;
// Spring Framework'te bu sınıfı bir bileşen (component) olarak işaretlemek için kullanılır.

import java.util.Date;
// Tarih ve saat bilgilerini işlemek için gerekli sınıf.

import io.jsonwebtoken.security.Keys;
// Güvenli anahtarlar oluşturmak için kullanılan sınıf.

import javax.crypto.SecretKey;
// Şifreleme anahtarı (SecretKey) oluşturmak için kullanılan sınıf.

@Component
// Bu sınıfı bir Spring bileşeni olarak işaretler. Spring IOC konteyneri tarafından yönetilir ve diğer sınıflar tarafından enjekte edilebilir.
public class _8_JwtUtils {

    // JWT işlemleri için kullanılacak gizli bir anahtar. HS512 algoritması ile oluşturuluyor.
    private final SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // JWT'nin geçerlilik süresi. Burada 1 gün (86400000 milisaniye) olarak ayarlanmış.
    private final long jwtExpirationMs = 86400000;

    // Kullanıcı adı (username) bilgisine dayalı olarak JWT oluşturur.
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username) // JWT'nin konusu, genellikle kullanıcı adı burada ayarlanır.
                .setIssuedAt(new Date()) // Token oluşturulma tarihi.
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Tokenin geçerlilik süresi.
                .signWith(jwtSecret) // Tokeni oluşturmak için gizli anahtarla imzalama.
                .compact(); // JWT'yi oluşturur ve String olarak döner.
    }

    // JWT'den kullanıcı adını ayrıştırır.
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret) // Doğrulama için gizli anahtarı kullanır.
                .build()
                .parseClaimsJws(token) // Tokeni ayrıştırır ve doğrular.
                .getBody() // Tokenin gövdesini (claims) alır.
                .getSubject(); // Gövdedeki kullanıcı adı bilgisini döner.
    }

    // JWT'nin geçerli olup olmadığını kontrol eder.
    public boolean validateJwtToken(String authToken) {
        try {
            // Tokeni ayrıştırır ve imzasını doğrular.
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true; // Token geçerliyse true döner.
        } catch (JwtException e) {
            // Token geçersizse bir hata yakalanır ve mesaj konsola yazdırılır.
            System.err.println("JWT Hatası: " + e.getMessage());
        }
        return false; // Token geçersizse false döner.
    }
}
