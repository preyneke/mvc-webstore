package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to the Web Store!");
		model.addAttribute("tagline", "The one and only Amazing Web Store!");
		
		return "welcome";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return"welcome";
	}
	
	

}
