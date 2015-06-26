package com.royesta.webapp.entity.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Roy Royesta (royesta lab)
 */
public enum Role implements GrantedAuthority {
	
	ROLE_ADMIN,
	ROLE_USER;
	
	// This GrantedAuthority interface implementation is to support SpringSecurity's role management cleanly
	@Override
	public String getAuthority() {
		return name();
	}
	
}
