package com.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.login.model.UserDetails;
import com.login.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
		
	   @Autowired
		UserService userService;
		

		@GetMapping("/")
		public String index() {
			return "index"; 
		}
		
//		@GetMapping("/signin")
//		public String login() {
//			return "login";
//		}
		
		@GetMapping("/register")
		public String register() {
			return "register";
		}
		
		
		
		@PostMapping("/createUser")
		public String createUser( UserDetails user,RedirectAttributes redirectAttributes) throws Exception {
			
			String message=userService.checkByEmail(user,user.getEmail());
			
			//
			if(message=="error") {
				redirectAttributes.addFlashAttribute("error", "User Already Exists!!");
			 
			}
			else {
				userService.createUser(user);
				redirectAttributes.addFlashAttribute("success", "Registered Successfully");
			}

		
			return "redirect:/register";
		}
		
}