package com.dianda.auth.util.nosql.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis",ignoreUnknownFields = true)
@Data
public class RedisSettings {
	
	public Integer database;
	public String host;
	public Integer port;
	public Integer timeout;
	
}
