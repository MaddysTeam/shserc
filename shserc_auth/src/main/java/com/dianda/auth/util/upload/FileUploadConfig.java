package com.dianda.auth.util.upload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

@Configuration
@EnableSwagger2
public class FileUploadConfig {
	
	/**
	 * 限制上传文件大小
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement( ) {
		MultipartConfigFactory factory = new MultipartConfigFactory ( );
		//单个文件最大 5m 可以使用读取配置
		factory.setMaxFileSize ( "5120KB" ); //KB,MB
		/// 设置总上传数据总大小 50m
		factory.setMaxRequestSize ( "512000KB" );
		
		return factory.createMultipartConfig ( );
	}
	
}
