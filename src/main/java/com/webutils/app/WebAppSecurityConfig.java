package com.webutils.app;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
		// http.authorizeRequests().anyRequest().authenticated();
		// http.formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
		// .loginPage("/login").permitAll().and().logout()
		// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		// .logoutSuccessUrl("/login").permitAll();
		/*
		 * http.addFilterBefore(new SessionRepositoryFilter(sessionRepository),
		 * ChannelProcessingFilter.class);
		 */
	}

}