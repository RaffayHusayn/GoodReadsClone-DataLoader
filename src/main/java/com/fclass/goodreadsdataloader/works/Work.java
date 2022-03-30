package com.fclass.goodreadsdataloader.works;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import java.time.LocalDate;
import java.util.List;

@Table(value = "work_by_id")
public class Work {
    /*
    Attributes:
        1. Work ID
        2. Description
        3. Work Title
        4. Author IDs List
        5. Author Names List
        6. Covers List
        7. Published Date

     */

    @PrimaryKeyColumn(name = "work_id" , ordinal =0 , type= PrimaryKeyType.PARTITIONED)
    @Id
    private String id;

    @Column(value = "work_title")
    @CassandraType(type = Name.TEXT)
    private String title;


    @Column(value = "description")
    @CassandraType(type = Name.TEXT)
    private String description;

    @Column(value = "author_ids")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorIds;

    @Column(value = "author_names")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorNames;

    @Column(value = "covers")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> coverIds;

    @Column(value = "published_date")
    @CassandraType(type = Name.DATE)
    private LocalDate publishedDate;

    public Work(String id, String title, String description, List<String> authorIds, List<String> authorNames, List<String> coverIds, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.coverIds = coverIds;
        this.publishedDate = publishedDate;
    }

    public Work() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<String> authorIds) {
        this.authorIds = authorIds;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public List<String> getCoverIds() {
        return coverIds;
    }

    public void setCoverIds(List<String> coverIds) {
        this.coverIds = coverIds;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
