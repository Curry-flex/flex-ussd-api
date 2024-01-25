package com.ussd.com.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ussd.com.enums.MenuOptionActions;

import lombok.Data;

@Data
public class MenuOption {
	private String type;
	private String response;
	
	@JsonProperty("next_menu_level")
	private String next_menu_level;
	
	private MenuOptionActions actions;

}
