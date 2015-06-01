package com.uslive.rabyks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uslive.rabyks.config.CustomTokenBasedRememberMeService;
import com.uslive.rabyks.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired 
	private CustomTokenBasedRememberMeService tokenBasedRememberMeService;
	
	@Autowired 
	private RememberMeAuthenticationProvider rememberMeAuthenticationProvider;
 
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf()
        		.disable()
            .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/user*").permitAll()
                .anyRequest().authenticated()
			.and()
				.formLogin()
			    .loginPage("/")
			    .loginProcessingUrl("/loginprocess")
			    .failureUrl("/mobile/app/sign-in?loginFailure=true")
                .permitAll().and()
                .rememberMe().rememberMeServices(tokenBasedRememberMeService);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(bCryptPasswordEncoder());
		auth.authenticationProvider(rememberMeAuthenticationProvider);
	}
		 
	@Bean 
	@Override 
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
		 
	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}