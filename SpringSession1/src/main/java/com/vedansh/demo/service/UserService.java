package com.vedansh.demo.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vedansh.demo.entity.UserDetails;

public interface UserService {
	
	void register(final UserDetails user) throws  Exception;
	

	String checkByEmail(UserDetails user, String email)
			throws Exception;
	
	
}
