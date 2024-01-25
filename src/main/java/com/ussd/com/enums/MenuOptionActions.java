package com.ussd.com.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuOptionActions {

	  REGISTER_CUSTOMER("REGISTER_CUSTOMER"),
	  GET_CUSTOMER_INFO("GET_CUSTOMER_INFO");
	  
	  private String action;

	private MenuOptionActions(String action) {
		this.action = action;
	}
	
	@JsonValue
	public String getAction()
	{
		return action;
	}
	
	  
}
