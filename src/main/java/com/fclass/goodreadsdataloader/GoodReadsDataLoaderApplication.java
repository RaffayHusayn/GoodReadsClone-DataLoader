package com.fclass.goodreadsdataloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class GoodReadsDataLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodReadsDataLoaderApplication.class, args);
    }

    /*
    Using the downloaded Secure-Connect-Goodreads bundle to connect this SpringBoot
    application to the remote Astra.Datastax instance
     */
    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
