package br.com.spring.mvc.basics.model.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class RoleVoter implements AccessDecisionVoter<Object> {

	private String rolePrefix = "ROLE_";

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		// TODO Auto-generated method stub
		return 0;
	}

}
