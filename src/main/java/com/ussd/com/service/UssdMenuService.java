package com.ussd.com.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ussd.com.entity.Menu;
import com.ussd.com.entity.config.HelperClass;

@Service
public class UssdMenuService {

	 private HelperClass helperClass;
	 
	 @Autowired
	 public UssdMenuService(HelperClass helperClass)
	 {
		 this.helperClass = helperClass;
	 }
	 
	 public Map<String,Menu> loadMenus() throws IOException
	 {
		;
		return helperClass.loadJsonFile();
		 
	 }
}
