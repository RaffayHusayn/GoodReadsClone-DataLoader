package com.fclass.goodreadsdataloader;

import com.fclass.goodreadsdataloader.author.Author;
import com.fclass.goodreadsdataloader.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class GoodReadsDataLoaderApplication {
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(GoodReadsDataLoaderApplication.class, args);

    }
    @PostConstruct
    public void start(){
        Author author = new Author();
        author.setId("1");
        author.setName("raffay");
        author.setPersonalName("personal raffay");
        authorRepository.save(author);
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
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
