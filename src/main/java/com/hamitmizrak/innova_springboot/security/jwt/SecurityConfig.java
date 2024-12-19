package com.hamitmizrak.innova_springboot.security.jwt;


import com.hamitmizrak.innova_springboot.bean.PasswordEncoderBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


// LOMBOK
@RequiredArgsConstructor

// SECURITY
@Configuration
@EnableWebSecurity //Web Security kullanmak
public class SecurityConfig {

    // Injection
    private final _8_JwtUtils jwtUtils;
    private final PasswordEncoderBean passwordEncoderBean;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Static WEB Dosyalarına erişimi (HTML,CSS,JS)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                .requestMatchers(new AntPathRequestMatcher("/html/**"))
                .requestMatchers(new AntPathRequestMatcher("/css/**"))
                .requestMatchers(new AntPathRequestMatcher("/js/**"))
                .requestMatchers(new AntPathRequestMatcher("/img/**"))
                .requestMatchers(new AntPathRequestMatcher("/lib/**"));
    } //end webSecurityCustomizer


    // http://localhost:4444/
    // http://localhost:4444/h2-console/
    // http://localhost:4444/swagger-ui/index.html
    // http://localhost:4444/api/address/find/1
    // İzin verilmesi gereken Path
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector, _9_JwtFilter jwtFilter) throws Exception { //Database üzerinden
        // CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Web Page
        httpSecurity.authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(new MvcRequestMatcher(introspector, "/")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/index")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/index.html")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/index.htm")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/login")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/logout")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/admin")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/swagger-ui/**")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/h2-console/**")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/api/auth/**")).permitAll()
                                //.requestMatchers(new MvcRequestMatcher(introspector, "/api/**")).permitAll()
                                .anyRequest().authenticated()
                )
                // Not: Eğer Database üzerinden kayıt istiyorsan jwtFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // POSTMAN kullanabilmek için (Http Aç)
                .httpBasic(Customizer.withDefaults())

                // Page Form İzin ver
                //.formLogin(Customizer.withDefaults())

                // Logout
                .logout().logoutUrl("/logout").invalidateHttpSession(true);
        return httpSecurity.build();
    }

} // end SecurityFilterChain

