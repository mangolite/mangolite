package org.spamjs.mangolite.app;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAppSecurityConfig.
 */
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
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