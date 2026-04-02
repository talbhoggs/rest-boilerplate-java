package com.gmail.dev.camper.boilerplate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//1. Security Config class
@Configuration
@EnableMethodSecurity // enable method level
public class SecurityConfig {

    
    // 2. User detail service
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails admin = User.withUsername("admin")
                                .authorities("BASIC","SPECIAL")
                                .roles("superuser")
                                .password(encoder.encode("password"))
                                .build(); 

        UserDetails user = User.withUsername("user")
                                .authorities("BASIC")
                                .roles("basic")
                                .password(encoder.encode("password"))
                                .build(); 

        return new InMemoryUserDetailsManager(admin, user);
    }

    // 3. Password Encounder
    // Need this because spring does not allow 
    // not encrypted password (raw)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 4: filter Chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // without enable method level security
        //return httpSecurity.csrf(
                //csrc->csrc.disable()) // need to disable this so that we can authenticate Post, Delete etc
                //.authorizeHttpRequests(authorize-> {
                    //authorize.requestMatchers(HttpMethod.GET, "/open").permitAll();
                    //authorize.requestMatchers(HttpMethod.GET, "/close").authenticated();
                    //authorize.requestMatchers(HttpMethod.GET, "/special").hasAnyAuthority("SPECIAL");
                    //authorize.requestMatchers(HttpMethod.GET, "/basic").hasAnyAuthority("BASIC","SPECIAL");
                    //authorize.anyRequest().authenticated();
        //}).httpBasic(Customizer.withDefaults())
        //.build();
        return httpSecurity.csrf(
                csrc->csrc.disable()) // need to disable this so that we can authenticate Post, Delete etc
                .authorizeHttpRequests(authorize-> {
                    authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults())
        .build();

    }

}

// 5. to enable method level Security
// 1. Add @EnableMethodSecurity
// 2. Add roles
// 3. Add annotation -> @PreAuthorize("hasRole('superuser') or hasRole('basic')")

