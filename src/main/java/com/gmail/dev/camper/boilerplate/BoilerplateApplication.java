package com.gmail.dev.camper.boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    SpringApplication.run(BoilerplateApplication.class, args);
  }
}
