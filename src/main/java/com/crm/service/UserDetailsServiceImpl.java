package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crm.dto.UserDetailsDTO;
import com.crm.entity.User;
import com.crm.repository.UserRepository;

@Service 
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		//Nếu email gửi từ client lên không có trong database
				if(user == null)
					throw new UsernameNotFoundException("Email không tồn tại!");
				//Lấy ra tên quyền tương ứng với user
				//User join đến role => sau đó lấy ra tên role 
				String roleName = user.getRole().getName();
				
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new  SimpleGrantedAuthority(roleName));
				//Tạo đối tượng UserDetailsDto
				UserDetailsDTO userDetailsDto = new UserDetailsDTO(user.getEmail(), user.getPassword(),authorities );
				userDetailsDto.setFullname(user.getFullname());
				System.out.println(userDetailsDto.getFullname());
				
				return userDetailsDto;
	}


}
