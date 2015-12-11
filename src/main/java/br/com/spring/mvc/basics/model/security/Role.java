package br.com.spring.mvc.basics.model.security;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

	/**
	 * Auto-generated 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}
}
