package com.dianda.common.util.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cache",ignoreUnknownFields=true)
@Data
public class CacheSettings {

	private int maxKeys;
	private int expireSeconds;
	private String type; // memory or redis

}
