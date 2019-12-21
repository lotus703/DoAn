package com.crm.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.LoginDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/admin/login")
public class AdminLoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("")
	public Object login(@RequestBody LoginDTO loginDto) {
		try {
		// Bước 1: gọi hàm đăng nhập của Spring Security
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
		
		//Bước 2: Lưu thông tin đăng nhập vào context
				SecurityContextHolder.getContext().setAuthentication(authentication);
				Date date = new Date();
		//Bước 3: tạo token
		String token = Jwts
				.builder()
				.setSubject(loginDto.getEmail())
				.setIssuedAt(date)
				.setExpiration(new Date( date.getTime() + 864000000L))
				.signWith(SignatureAlgorithm.HS512, "1234")
				.compact();
		return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Sai email hoặc mật khẩu!", HttpStatus.BAD_REQUEST);
	}
}
