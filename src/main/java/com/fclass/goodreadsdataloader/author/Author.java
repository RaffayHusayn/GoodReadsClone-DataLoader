package com.fclass.goodreadsdataloader.author;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

//spring data cassandra is not the same as spring data JPA so we don't have to use Entity annotation
@Table(value = "author_by_id")
public class Author {

    @PrimaryKeyColumn(name = "author_id" , ordinal = 0 , type= PrimaryKeyType.PARTITIONED)
    @Id
    private String id;

    @Column(value = "author_name")
    @CassandraType(type = Name.TEXT)
    private String name;

    @Column( value = "personal_name")
    @CassandraType(type = Name.TEXT)
    private String personalName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }
}
