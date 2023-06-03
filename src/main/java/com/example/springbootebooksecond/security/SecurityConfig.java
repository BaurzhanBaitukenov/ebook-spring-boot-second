package com.example.springbootebooksecond.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/","/login/**","/clubs", "/register/**", "/css/**", "/js/**").permitAll()
                .requestMatchers("/clubs/new").hasAnyAuthority("ADMIN")
                .requestMatchers("/clubs/{id}/edit").hasAnyAuthority("ADMIN")
                .requestMatchers("/clubs/{id}").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/book/{email}").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/profile/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/users/**").hasAuthority("ADMIN")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/clubs")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
        return http.build();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
