package com.vedansh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vedansh.demo.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.vedansh.demo.entity.UserDetails user= userRepository.findByEmail(email);
		
		if(user!=null) {
			return new CustomerUserDetails(user);
		}
		 throw new UsernameNotFoundException("User not Available");
	}

}
