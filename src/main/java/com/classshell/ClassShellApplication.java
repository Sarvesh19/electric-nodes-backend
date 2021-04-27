package com.classshell;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;

@SpringBootApplication
public class ClassShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassShellApplication.class, args);
	}
	// git push master main
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE",
                        "GET", "POST");
            }
        };
    }
	
	private class ServerAddress {
		private String cluster;
		private Integer port;
		ServerAddress(String cluster, Integer port){
			this.cluster = cluster;
			this.port = port;
		}
	}
	
	

}
