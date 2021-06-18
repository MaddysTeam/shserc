package com.dianda;


import com.dianda.common.security.shiro.jwt.JwtSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigurationProperties({ JwtSettings.class,})
@EnableAspectJAutoProxy()
//@MapperScan("com.dianda.*.mapper*")
@CrossOrigin(origins = {"http://127.0.0.1:8080", "null"})
public class ServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run( ServiceApplication.class, args);
	}

}
