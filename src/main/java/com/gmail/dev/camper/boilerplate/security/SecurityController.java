package com.gmail.dev.camper.boilerplate.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    
    @GetMapping("/open") 
    public String open() {
        return "OPEN";
    }

    @GetMapping("/close") 
    public String close() {
        return "close";
    }

    @GetMapping("/basic")
    @PreAuthorize("hasRole('superuser') or hasRole('basic')")
    public String basic() {
        return "BASIC";
    }

    @GetMapping("/special")
    @PreAuthorize("hasRole('superuser')")
    public String special() {
        return "SPECIAL";
    }
}
