package com.ussd.com.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@RedisHash(value = "sessions", timeToLive = 180)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UssdSession implements Serializable {
	
	    private static final long serialVersionUID = 1L;

	    @Id
	    private String id;
	    private String sessionId;
	    private String serviceCode;
	    private String networkCode;
	    private String phoneNumber;
	    private String text;
	    private String previousMenuLevel;
	    private String currentMenuLevel;

}
