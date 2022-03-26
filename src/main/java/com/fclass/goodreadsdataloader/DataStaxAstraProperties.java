package com.fclass.goodreadsdataloader;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
/*
ConfigurationProperties is a specific annotation that allows us to pull up configuration from property files
i.e : applicaiton.properties etc. It is basically a way to get externalized configuration
prefix is used to mention only take the properties from application.properties that start with "datastax.astra" and after
the prefix if the name matches the member of the class (case doesn't matter, dashes (-) or underscore (_) doesn't matter)
then the value from the application.properties files is assigned to the member of this class USING SETTER

-- We can do something like this with @Value annotaion where member variables can get their values from the application.properties
but this is a more powerful way of getting all the properties at once in a bean whose only purpose is to get the values

 */
@ConfigurationProperties(prefix = "datastax.astra")
@Configuration
public class DataStaxAstraProperties {

    public File secureConnectBundle;

    public File getSecureConnectBundle() {
        return secureConnectBundle;
    }

    public void setSecureConnectBundle(File secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }
}
