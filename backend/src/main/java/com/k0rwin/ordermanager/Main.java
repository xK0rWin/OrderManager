package com.k0rwin.ordermanager;

import com.k0rwin.ordermanager.entity.Coke;
import com.k0rwin.ordermanager.entity.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@EntityScan("com.k0rwin.ordermanager.entity")
@EnableJpaRepositories(basePackages = "com.k0rwin.ordermanager.repository")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
