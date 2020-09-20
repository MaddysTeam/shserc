package com.dianda.shserc;


import com.dianda.shserc.util.shiro.jwt.JwtSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigurationProperties({ JwtSettings.class,})
@MapperScan("com.dianda.*.mapper*")
@CrossOrigin(origins = {"http://127.0.0.1:8080", "null"})
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run( AuthApplication.class, args);
	}

}
