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
    public String basic() {
        return "BASIC";
    }

    @GetMapping("/special")
    public String special() {
        return "SPECIAL";
    }
}
