package com.hamitmizrak.innova_springboot.security.jwt;

import com.hamitmizrak.innova_springboot.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// LOMBOK
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/auth")
public class _6_AuthApi {

   // Injection
    private final AuthenticationManager authenticationManager;
    private final _8_JwtUtils jwtUtils;
    private final PasswordEncoderBean passwordEncoder;
    private final _5_IUserRepository userRepository;

    // http://localhost:4444/api/auth/login
    /*
    curl -X POST http://localhost:4444/api/auth/login \
    -H "Content-Type: application/json" \
    -d '{
      "username": "root",
      "password": "root"
    }'
    * */
    @PostMapping("/login")
    public String authenticateUser(@RequestBody _1_LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String token = jwtUtils.generateJwtToken(authentication.getName());
        System.out.println(token);
        return token;
    }

    // http://localhost:4444/api/auth/register
    /*
    curl -X POST http://localhost:4444/api/auth/register \
    -H "Content-Type: application/json" \
    -d '{
      "username": "root",
      "password": "root"
    }'
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody _2_RegisterRequest registerRequest) {
        _4_UserEntity user = new _4_UserEntity();
        user.setUsername(registerRequest.getUsername());

        // Şifreyi şifrele
        user.setPassword(passwordEncoder.getPasswordEncoderBeanMethod().encode(registerRequest.getPassword()));
        user.setRole(_3_Role.ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok("Kayıt başarılı!");
    }

    // Postman
    // GET
    // http://localhost:4444/api/address/v1.0.0/find/1
    // Auth Type : Bearer Token
    // Token Ekle
}




