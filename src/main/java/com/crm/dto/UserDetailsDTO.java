package com.crm.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserDetailsDTO extends User{
	
	private static final long serialVersionUID = 1L;
	//sửa username thành email
	public UserDetailsDTO(String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super(email, password, authorities);
		 
	}
	private String fullname;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
