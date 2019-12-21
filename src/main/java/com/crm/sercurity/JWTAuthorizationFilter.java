package com.crm.sercurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	UserDetailsService _userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		_userDetailsService = userDetailsService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// Lấy token từ header do người dùng gửi lên
		String tokenBearer = request.getHeader("Authorization");
		System.out.println(tokenBearer);
		// Kiểm tra xem token có bắt đầu bằng Bearer hay không
		if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
			// Xóa tiền tố (Bearer)

			String token = tokenBearer.replace("Bearer ", "");
			System.out.println(token);

			// Giải mã token để lấy thông tin (email)
			String email = Jwts.parser().setSigningKey("1234").parseClaimsJws(token).getBody().getSubject();

			// Lấy thông tin user từ db
			UserDetails user = _userDetailsService.loadUserByUsername(email);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			// gái thông tin vào security context
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}
