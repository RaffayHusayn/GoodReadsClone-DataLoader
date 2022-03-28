package com.fclass.goodreadsdataloader;

import com.fclass.goodreadsdataloader.author.Author;
import com.fclass.goodreadsdataloader.author.AuthorRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
//@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class GoodReadsDataLoaderApplication {
    private AuthorRepository authorRepository;
    @Value("${datadump.location.authors}")
    private String authorsDumpLocation;
    @Value("${datadump.location.works}")
    private String worksDumpLocation;

    public static void main(String[] args) {
        SpringApplication.run(GoodReadsDataLoaderApplication.class, args);

    }

    // Using Setter Injection to inject AuthorRepository Bean
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    /*
    ================================================================================
        Adding manually created Authors in 2 ways:
        1. Using @PostConstructor
        2. Using CommnandLineRunner

        use whatever you like, I'll use the CommandLineRunner
    ================================================================================
     */
    //========= Way # 1 : Using PostConstructor ======================
    @PostConstruct
    public void start(){
//        Author author = new Author();
//        author.setId("1");
//        author.setName("raffay");
//        author.setPersonalName("personal raffay");
//        authorRepository.save(author);
        intiAuthors();
        System.out.println("done");
    }

    //========= Way # 2 : Using CommandLineRunner =====================
//    @Bean
//    CommandLineRunner runner(AuthorRepository authorRepository) {
//        return args -> {
//            authorRepository.save(new Author("2", "Ayesha", "Ayesha Personal Name"));
//            authorRepository.save(new Author("3", "Nabia", "Nabia Personal Name"));
//        };
//    }


    /*
    Using the downloaded Secure-Connect-Goodreads bundle to connect this SpringBoot
    application to the remote Astra.Datastax instance
     */
    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }


    private void intiAuthors(){
        Path path = Paths.get(authorsDumpLocation);
            try (Stream<String> lines = Files.lines(path)) {
                System.out.println("We have the lines");
                lines.forEach(line -> {
                    // 1. Read and Parse each line
                    String jsonString = line.substring(line.indexOf("{")); //start the substring from { to the end
                    System.out.println("jsonString : "+ jsonString);

                    try {
                       JSONObject jsonObject = new JSONObject(jsonString);

                        //2. Construct the Author Object
                        Author author = new Author();
                        author.setName(jsonObject.optString("name"));
                        author.setPersonalName(jsonObject.optString("personal_name"));
                        author.setId(jsonObject.optString("key").replace("/authors/",""));

                        //3. Save the Author Object in the remote instance using Repository
                        authorRepository.save(author);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                });
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

    }


}
