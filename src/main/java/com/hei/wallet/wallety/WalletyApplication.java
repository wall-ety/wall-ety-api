package com.hei.wallet.wallety;

import com.hei.wallet.wallety.repository.conf.ConnectionProperties;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WalletyApplication {
    @Autowired
    private ConnectionProperties connectionProperties;

    public static void main(String[] args) {
        SpringApplication.run(WalletyApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doMigration() {
        Flyway.configure()
                .dataSource(
                        connectionProperties.getUrl(),
                        connectionProperties.getUsername(),
                        connectionProperties.getPassword())
                .load()
                .migrate();
    }
}