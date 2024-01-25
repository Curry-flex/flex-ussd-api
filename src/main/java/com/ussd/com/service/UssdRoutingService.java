package com.ussd.com.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ussd.com.entity.Menu;
import com.ussd.com.entity.MenuOption;
import com.ussd.com.entity.UssdSession;
import com.ussd.com.entity.config.HelperClass;
import com.ussd.com.entity.config.serviceImpl.UssdServiceImpl;



@Service
public class UssdRoutingService {

	@Autowired
	private HelperClass helperClass;
	@Autowired
	private UssdMenuService ussdMenuService;
	@Autowired
	private UssdSessionService ussdSessionService;
	
	@Autowired
	private UssdServiceImpl serviceImpl;
	
	@Autowired
	private SessionService sessionService;
	
    
	
//	@Autowired
//	public UssdRoutingService(HelperClass helperClass, UssdMenuService ussdMenuService,
//			UssdSessionService ussdSessionService) {
//		this.helperClass = helperClass;
//		this.ussdMenuService = ussdMenuService;
//		this.ussdSessionService = ussdSessionService;
//	}


	public String menuLevelRouter(String sessionId, String serviceCode, String phoneNumber, String text, String networkCode) throws IOException
	{
		 Map<String,Menu> menus=ussdMenuService.loadMenus(); //load menus
		 UssdSession session=checkAndSetSession(sessionId, serviceCode, phoneNumber, text, networkCode);
		 
//		 if(text.length() > 0)
//		 {
//			 return getNextMenuItem(session, menus);
//		 }else {
//			 return menus.get(session.getCurrentMenuLevel()).getText();
//		 }
		 
		  return text.length() > 0 ? getNextMenuItem(session, menus) : menus.get(session.getCurrentMenuLevel()).getText();
//		 
	 }
	
	
	  private String getNextMenuItem(UssdSession session, Map<String, Menu> menus) throws IOException {
		  
		  String[] levels = session.getText().split("\\*");
	        String lastValue = levels[levels.length - 1];
	        Menu menuLevel = menus.get(session.getCurrentMenuLevel());

	        if(Integer.parseInt(lastValue) <= menuLevel.getMax_selections()) {
	            MenuOption menuOption = menuLevel.getMenuOptions().get(Integer.parseInt(lastValue) - 1);
	            return processMenuOption(session, menuOption);
	        }

	        return "CON ";
	  }
	  
	    public String processMenuOption(UssdSession session, MenuOption menuOption) throws IOException {
	        switch (menuOption.getType()) {
	            case "response":
	                return processMenuOptionResponses(menuOption);
	            case "level":
	                updateSessionMenuLevel(session, menuOption.getNext_menu_level());
	                return getMenu(menuOption.getNext_menu_level());
	            default:
	                return "CON ";
	        }
	    }
	    
	    public String getMenu(String menuLevel) throws IOException {
	        return ussdMenuService.loadMenus().get(menuLevel).getText();
	    }
	    
	    public String processMenuOptionResponses(MenuOption menuOption) {
	        String response = menuOption.getResponse();
	        Map<String, String> variablesMap = new HashMap<>();

	        switch (menuOption.getActions()) {
	            case REGISTER_CUSTOMER:
	                variablesMap.put("account_balance", "10000");
	                break;
	            case GET_CUSTOMER_INFO:
	                variablesMap.put("fname", "curry");
	                variablesMap.put("lname", "flex");
	                break;
	       
	        }

	        response = replaceVariable(variablesMap, response);
	        return response;
	    }
	    
	    public String replaceVariable(Map<String, String> variablesMap, String response) {
	        return new StringSubstitutor(variablesMap).replace(response);
	    }

	    public UssdSession updateSessionMenuLevel(UssdSession session, String menuLevel) {
	        session.setPreviousMenuLevel(session.getCurrentMenuLevel());
	        session.setCurrentMenuLevel(menuLevel);
	        return sessionService.update(session);
	    }


	public UssdSession checkAndSetSession(String sessionId, String
	            serviceCode, String phoneNumber, String text, String networkCode) {
	       // UssdSession session = serviceImpl.findById(sessionId);
	       
	       
	        UssdSession session = sessionService.get(sessionId);
	      
	        System.out.println("session is " + session);

	        if (session != null) {
	            session.setText(text);
	            return sessionService.update(session);
	        }

	        session = new UssdSession();
	        session.setCurrentMenuLevel("1");
	        session.setPreviousMenuLevel("1");
	        session.setId(sessionId);
	        session.setPhoneNumber(phoneNumber);
	        session.setServiceCode(serviceCode);
	        session.setText(text);

	        return sessionService.createUssdSession(session);
	    }
	
	
}
