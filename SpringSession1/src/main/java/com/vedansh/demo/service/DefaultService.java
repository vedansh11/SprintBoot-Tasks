package com.vedansh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vedansh.demo.entity.UserDetails;
import com.vedansh.demo.repository.UserRepository;

@Service
public class DefaultService implements UserService{
	
	@Autowired
	private UserRepository userRepository; 

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	RedirectAttributes redirectAttributes;

	@Override
	public void register(UserDetails user) throws Exception {
		// TODO Auto-generated method stub
		
		encodePassword(user);
		user.setRole("ROLE_USER");
		userRepository.save(user);
			
	}

	


	public void encodePassword(UserDetails userDetails) {
		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
	}




	@Override
	 public String checkByEmail(UserDetails user,String email) throws Exception {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(email))
		{
			return "error";
		}
		register(user);
		return "success";
		
	}




	
	

}
