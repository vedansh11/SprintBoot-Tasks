package com.vedansh.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vedansh.demo.entity.UserDetails;
import com.vedansh.demo.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	private void userDetails(Model model , Principal p) {
		String email = p.getName();
		UserDetails user=userRepository.findByEmail(email);
	
		model.addAttribute("user",user);
	}
	@GetMapping("/")
	public String home() {
		return "admin/home";
	}
}
