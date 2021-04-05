package com.dianda.common.security.shiro.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Data
public class JwtSettings {
	
	public Integer expiredSeconds;
	public String secret;
	
}
