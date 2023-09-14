package com.ssafy.tott.global.security;

import com.ssafy.tott.auth.support.JwtAccessDeniedHandler;
import com.ssafy.tott.auth.support.JwtAuthenticationEntryPoint;
import com.ssafy.tott.auth.support.JwtSecurityConfig;
import com.ssafy.tott.auth.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/budget/auth/**").authenticated()
                .antMatchers("/api/house/auth/**").authenticated()
                .antMatchers("/api/member/auth/**").authenticated()
                .antMatchers("/api/geo/auth/**").authenticated()
                .antMatchers("/api/region/auth/**").authenticated()
                .antMatchers("/api/wishlist/auth/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
