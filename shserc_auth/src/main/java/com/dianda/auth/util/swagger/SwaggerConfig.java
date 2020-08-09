package com.dianda.auth.util.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket CreateApi(){
		ApiInfo info=new ApiInfoBuilder().build();
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controllers"))
				.build()
				.apiInfo( info);
	}

}
