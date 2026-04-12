package com.gmail.dev.camper.boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BoilerplateApplication {

  public static void main(String[] args) {
    // Get the Java version
    String javaVersion = System.getProperty("java.version");
    String javaVendor = System.getProperty("java.vendor");
    String javaHome = System.getProperty("java.home");

    // Display the details
    System.out.println("Java Version: " + javaVersion);
    System.out.println("Java Vendor: " + javaVendor);
    System.out.println("Java Home: " + javaHome);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("password123"));
    System.out.println(encoder.encode("secret456"));
    System.out.println(encoder.encode("welcome789"));
    SpringApplication.run(BoilerplateApplication.class, args);
  }
}
