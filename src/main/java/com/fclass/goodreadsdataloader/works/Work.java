package com.fclass.goodreadsdataloader.works;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table(value = "work_by_id")
public class Work {

    @PrimaryKeyColumn(name = "work_id" , ordinal =0 , type= PrimaryKeyType.PARTITIONED)
    @Id
    private String id;

    @Column(value = "work_title")
    @CassandraType(type = Name.TEXT)
    private String title;

    @Column(value = "author_id")
    @CassandraType(type = Name.TEXT)
    private String authorId;

    //No Arg constructor
    public Work() {
    }

    // All Arg Constructor
    public Work(String id, String title, String authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
