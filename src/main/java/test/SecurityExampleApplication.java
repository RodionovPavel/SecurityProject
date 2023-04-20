package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SecurityExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityExampleApplication.class, args);
    }
}
