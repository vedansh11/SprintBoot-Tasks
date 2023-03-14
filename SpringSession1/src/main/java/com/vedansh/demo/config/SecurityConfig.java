package com.vedansh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationSuccessHandler aSuccessHandler;
	
	@Bean
     UserDetailsService getuserDetailsService() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
        		.disable()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**")
                .hasRole("USER")
                .requestMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .successHandler(aSuccessHandler)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/signin?invalid-session=true");
                
                
        return httpSecurity.build();
   }
    
    
     protected void configure (AuthenticationManagerBuilder auth) throws Exception{
    	auth.authenticationProvider(getdaoauthenticationProvider());
    }
    
    @Bean
    DaoAuthenticationProvider getdaoauthenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(getpasswordEncoder());
    authenticationProvider.setUserDetailsService(getuserDetailsService());
    return authenticationProvider;
    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
    	SessionRegistry sessionRegistry = new SessionRegistryImpl();
    	return sessionRegistry;
    }
	
}
