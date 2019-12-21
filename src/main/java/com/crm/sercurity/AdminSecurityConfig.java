package com.crm.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.crm")
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override // cấu hình đăng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Override //cấu hình bỏ qua không đăng nhập cho các file tĩnh
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}

	@Override // cấu hình phân quyền
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();

		http.csrf().disable()
		// Gặp link bắt đầu bằng /api/admin thì chạy các hàm
		.antMatcher("/**")
		.authorizeRequests()
		.antMatchers("/login**")
		.permitAll()
		.antMatchers("/user/edit/**","/user/add/**","/user/delete/**")
		.hasAnyRole("ADMIN","MANAGER")
		.antMatchers("/group/edit/**","/group/add/**","/group/delete/**")
		.hasAnyRole("ADMIN","MANAGER")
		.antMatchers("/task/edit/**","/task/add/**","/task/delete/**")
		.hasAnyRole("ADMIN","MANAGER")
		.anyRequest()
		.authenticated()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/error/403");
				//.and()
				//.formLogin()
				http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=123");
                //.and()
                http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID");
            
				
//				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//				http.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
				
	}
}
