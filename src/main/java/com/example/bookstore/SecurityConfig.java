package com.example.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/login", "/css/**").permitAll() 
                        .requestMatchers("/delete/**").hasRole("ADMIN") // Only allow Admin to delete
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/Booklist", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/booklist")
                        .permitAll())
                .csrf(csrf -> csrf.disable()); 

        return http.build();
    }

    @Bean
     public UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder();
        
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password")) 
            .roles("USER")
            .build();
        
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin")) 
            .roles("ADMIN", "USER")
            .build();
        
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
