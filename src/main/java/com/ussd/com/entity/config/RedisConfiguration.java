package com.ussd.com.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
@PropertySource("application.properties")
public class RedisConfiguration extends CachingConfigurerSupport {
	
    @Value("${decoded.cache.host}")
    private String host;

    @Value("${decoded.cache.port}")
    private int port;

    @Value("${decoded.cache.password}")
    private String password;

    @Value("${decoded.cache.default-ttl}")
    private String defaultTTL;
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory()
	{
		RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
		conf.setHostName(host);
		conf.setPort(port);
		conf.setPassword(password);
		
		return new LettuceConnectionFactory(conf);
	}

}
