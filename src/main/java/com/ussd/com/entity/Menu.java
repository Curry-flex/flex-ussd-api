package com.ussd.com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Menu {
		@JsonProperty("id")
		private int id;
		
		@JsonProperty("menu_level")
		private int menu_level;
		
		@JsonProperty("text")
		
		private String text;
		
		@JsonProperty("menu_options")
		private List<MenuOption> menuOptions;
		
		@JsonProperty("action")
		private String action;
	
        @JsonProperty("has_input")
	    private boolean hasInput;

	    @JsonProperty("number_of_inputs")
	    private int numberOfInputs;

	    @JsonProperty("previous_menu")
	    private int previousMenu;

	    @JsonProperty("has_navigation_options")
	    private boolean hasNavigationOptions;

	    @JsonProperty("nav_options_args")
	    private int navOptionArgs;

	    @JsonProperty("menu_description")
	    private String menuDescription;

	
	@JsonProperty("max_selections")
	private Integer max_selections;

}
