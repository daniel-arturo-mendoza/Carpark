package com.damh.carpark.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class GUIConfigurationAPI {

  @Bean
  public Docket init() {
	  Contact contact = new Contact("Daniel Mendoza", "www.damh.com", "daniel.arturo.mendoza@gmail.com");
	  
	  ApiInfo apiInfo = new ApiInfo("Carpark", "Carpark API", "0.1", "N/A", contact,
		        					"N/A", "N/A", new ArrayList<VendorExtension>());
	  
	  Docket docket = new Docket(DocumentationType.SWAGGER_2);
	  docket.select().apis(RequestHandlerSelectors.basePackage("com.damh.carpark.controller"))
        		   .paths(PathSelectors.any()).build()
                   .apiInfo(apiInfo);
	  
	  return docket;
  }
}
