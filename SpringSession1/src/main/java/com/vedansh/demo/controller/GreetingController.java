package com.vedansh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vedansh.demo.entity.UserDetails;
import com.vedansh.demo.repository.UserRepository;
import com.vedansh.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
	public class GreetingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	//
	
	    @GetMapping("/greeting")
	    public String greeting( Model model) {
	        model.addAttribute("nam","Vedansh");
	        return "greeting";
	    }
	    

	    @GetMapping("/page1")
	    public String page1( Model model) {
	        model.addAttribute("nam","Vedansh");
	        return "pages/Page1";
	    }
	    
	    @GetMapping("/page2")
	    public String page2( Model model) {
	        model.addAttribute("nam","Vedansh");
	        return "pages/Page2";
	    }
	    
	    @GetMapping("/page3")
	    public String page3( Model model,HttpSession session) {
	    	
	    	HttpSession sess= session;
	    	System.out.println(sess);
	        model.addAttribute("nam",sess);
	        return "pages/Page3";
	    }
	    
	    @GetMapping("/")
		public String index() {
			return "index";
		}
	    
		@GetMapping("/register")
		public String register() {
			return "register";
		}

//		@GetMapping("/signin")
//		public String login() {
//			return "login";
//		}
		
		@PostMapping("/createuser")
		public String createUser( UserDetails user,RedirectAttributes redirectAttributes) throws Exception {
			
			String message=userService.checkByEmail(user,user.getEmail());
			
			//
			if(message=="error") {
				redirectAttributes.addFlashAttribute("error", "User Already Exists!!");
			 
			}
			else {
				userService.register(user);
				redirectAttributes.addFlashAttribute("success", "Registered Successfully");
			}

		
			return "redirect:/register";
		}
	
}
