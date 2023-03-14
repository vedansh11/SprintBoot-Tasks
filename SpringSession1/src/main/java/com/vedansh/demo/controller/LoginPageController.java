package com.vedansh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signin")
public class LoginPageController {
	
	@GetMapping
	public String login(@RequestParam(value="invalid-session" ,defaultValue = "false")boolean invalidSession,final Model model) {
		if(invalidSession) {
			model.addAttribute("invalidSession", "Session Expired, Please Re-Login");
			
		}
		return "login";
	}

}
