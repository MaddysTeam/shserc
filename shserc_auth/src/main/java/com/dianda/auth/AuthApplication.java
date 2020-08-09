package com.dianda.auth;

import com.dianda.auth.util.shiro.jwt.JwtSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ JwtSettings.class})
@MapperScan("com.dianda.*.mapper*")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run( AuthApplication.class, args);
	}

}
