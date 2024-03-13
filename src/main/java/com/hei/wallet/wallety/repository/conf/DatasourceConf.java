package com.hei.wallet.wallety.repository.conf;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConf {
    @Bean
    DataSource dataSource(){
        String databaseUrl = "DATABASE_URL";
        String databaseUsername = "DATABASE_USERNAME";
        String databasePassword = "DATABASE_PASSWORD";

        return DataSourceBuilder
                .create()
                .driverClassName("org.postgresql.Driver")
                .url(System.getenv(databaseUrl))
                .password(System.getenv(databasePassword))
                .username(System.getenv(databaseUsername))
                .build();
    }
}