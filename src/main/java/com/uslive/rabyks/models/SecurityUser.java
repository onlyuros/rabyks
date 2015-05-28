package com.uslive.rabyks.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uslive.rabyks.common.RoleEnum;
import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.User;

public class SecurityUser extends User implements UserDetails {   
	
	private static final long serialVersionUID = 1L; 
	
	public SecurityUser(User user) { 
		if(user != null) 
		{ 
			this.setId(user.getId()); 
			this.setEmail(user.getEmail()); 
			this.setPassword(user.getPassword());
			this.setNumber(user.getNumber());
			this.setRole(user.getRole()); 
		}	
	} 
	
	@Override public Collection<? extends GrantedAuthority> getAuthorities() { 
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		Set<Role> userRoles = (Set<Role>) this.getRole(); 
		if(userRoles != null) { 
			for (Role role : userRoles) { 
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole() == 0 ? RoleEnum.USER.toString() : RoleEnum.ADMIN.toString()); 
				authorities.add(authority); 
			} 
		} 
		return authorities; 
	}   
	
	@Override public String getPassword() { 
		return super.getPassword(); 
	}   
	
	@Override public String getUsername() { 
		return super.getEmail(); 
	}   
	
	@Override public boolean isAccountNonExpired() { 
		return true; 
	}   
	
	@Override public boolean isAccountNonLocked() { 
		return true; 
	}   
	
	@Override public boolean isCredentialsNonExpired() { 
		return true; 
	}   
	
	@Override public boolean isEnabled() { 
		return true; 
	}	

} 