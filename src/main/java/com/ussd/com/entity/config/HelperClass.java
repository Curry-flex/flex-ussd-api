package com.ussd.com.entity.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ussd.com.entity.Menu;


@Service
public class HelperClass {
	
	    @Autowired
	    private ResourceLoader resourceLoader;

	    private ObjectMapper objectMapper;
	    
	
	public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public Map<String, Menu> loadJsonFile() throws IOException {
//    	System.out.println("in load menu-from helper");
//        Resource resource = resourceLoader.getResource("classpath:menu.json");
//        
//        System.out.println("after resource loader");
//        InputStream input = resource.getInputStream();
//        String json = readFromInputStream(input);
//        return objectMapper.readValue(json, new TypeReference<Map<String, Menu>>() {
//        });
    	
    	 ObjectMapper objectMapper = new ObjectMapper();
         Resource resource = resourceLoader.getResource("classpath:menu.json");
         InputStream input = resource.getInputStream();
         String json = readFromInputStream(input);
         return objectMapper.readValue(json, new TypeReference<Map<String, Menu>>() {
         });
    }
    
    public LinkedHashMap<String, Object> loadJsonFileText(String fileName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:menu.json");
        InputStream input = resource.getInputStream();
        String json = readFromInputStream(input);
        return objectMapper.readValue(json, new TypeReference<LinkedHashMap<String, Object>>() {
        });
    }

}
