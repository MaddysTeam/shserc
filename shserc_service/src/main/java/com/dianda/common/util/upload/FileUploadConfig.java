package com.dianda.common.util.upload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

@Configuration
@EnableSwagger2
public class FileUploadConfig {
	
	/**
	 * multipart file upload config
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement( ) {
		MultipartConfigFactory factory = new MultipartConfigFactory ( );

		//TODO: set upload max file size , can read from config
		factory.setMaxFileSize ( DataSize.ofMegabytes(50) ); // default max is 50m

		//TODO: set max request size , can read from config
		factory.setMaxRequestSize (DataSize.ofMegabytes(500) ); // default max is 500m

		return factory.createMultipartConfig ( );
	}
	
}
