package com.ussd.com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ussd.com.entity.UssdSession;
import com.ussd.com.service.UssdRoutingService;
import com.ussd.com.service.UssdSessionService;



@RestController

public class ussdController {
   
	  @Autowired
	  private UssdRoutingService ussdRoutingService;
	  
       @Autowired
	    private UssdSessionService ussdSessionService;

//	    @Autowired
//	    public ussdController(UssdRoutingService ussdRoutingService, UssdSessionService ussdSessionService) {
//	        this.ussdRoutingService = ussdRoutingService;
//	        this.ussdSessionService = ussdSessionService;
//	    }

	    @PostMapping(path = "/pbz/v1/ussd", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	    public String incomingUssdRequestHandler(@RequestParam String text, @RequestParam String phoneNumber,
	                                             @RequestParam String networkCode, @RequestParam String serviceCode, @RequestParam String sessionId,@RequestParam String id) {

              System.out.println(id);
	        if (sessionId == null || serviceCode == null || networkCode == null || phoneNumber == null) {
	            return "END Service encountered an error, please try again";
	        }

	        try {
	            String response2Mno = ussdRoutingService.menuLevelRouter(sessionId, serviceCode, phoneNumber, text, networkCode);


//	            Thread thread = new Thread(() -> {
//	                if (response2Mno.startsWith("END")) {
//	                    Iterable<UssdSession> ussdSessions = ussdSessionService.findAll();
//	                    for (UssdSession session : ussdSessions) {
//	                        if (session != null) {
//	                            if (session.getId().equals(sessionId)) {
//	                                ussdSessionService.deleteById(session.getId());
//	                                break;
//	                            }
//	                        }
//	                    }
//	                }
//	            });
//	            thread.run();

	      return response2Mno;
	        } catch (IOException e) {
	            //Gracefully shut down in case of error
	            e.printStackTrace();
	            return "END Service is temporarily unavailable";
	        }

	    }


	    @ExceptionHandler
	    public String handleException(Exception e) {
	        e.printStackTrace();
	        return "END Service encountered any error, please try again";
	    }
}
