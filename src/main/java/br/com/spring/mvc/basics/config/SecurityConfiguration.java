package br.com.spring.mvc.basics.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Configuration of the web app.
 * @author mushira4
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers( "/products/form").hasRole("ADMIN")
				.antMatchers("/shopping/**").permitAll()
				.antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
				.antMatchers("/products/**").permitAll()
			.anyRequest()
				.authenticated()
			.	and()
				.formLogin();
	}
}
