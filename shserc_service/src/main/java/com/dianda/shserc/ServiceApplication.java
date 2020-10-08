package com.dianda.shserc;


import com.dianda.shserc.util.shiro.jwt.JwtSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
@EnableConfigurationProperties({ JwtSettings.class,})
@MapperScan("com.dianda.*.mapper*")
@CrossOrigin(origins = {"http://127.0.0.1:8080", "null"})
public class ServiceApplication {
 
	static Logger logger= LogManager.getLogger(ServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run( ServiceApplication.class, args);
		logger.info ( "test" );
	}

}
