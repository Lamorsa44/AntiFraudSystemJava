package antifraud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(POST, "/api/auth/user").permitAll()
                        .requestMatchers("/actuator/shutdown").permitAll()
                        .requestMatchers(GET, "/api/auth/list").hasAnyRole("ADMINISTRATOR", "SUPPORT")
                        .requestMatchers(POST, "/api/antifraud/transaction").hasRole("MERCHANT")
                        .requestMatchers("/api/antifraud/stolencard", "/api/antifraud/stolencard/**").hasRole("SUPPORT")
                        .requestMatchers("/api/antifraud/suspicious-ip", "/api/antifraud/suspicious-ip/**").hasRole("SUPPORT")
                        .requestMatchers("/api/auth/**").hasRole("ADMINISTRATOR")
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
