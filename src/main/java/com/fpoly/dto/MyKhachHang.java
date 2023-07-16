package com.fpoly.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class MyKhachHang extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private  String hoTen ;
	
	
	public MyKhachHang(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities ,String hoTen) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.hoTen = hoTen ;
	}


	
	


	



	
	
	

	

	
	
	
	
}
