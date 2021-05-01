package com.electricnodes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.electricnodes.config.ConfigurationMail;


@SpringBootApplication
@Import(value= {ConfigurationMail.class})
public class ElectricNodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricNodesApplication.class, args);
	}


	

}
