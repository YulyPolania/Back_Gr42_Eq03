// package com.Back_Gr42_Eq03.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.Back_Gr42_Eq03.service.impl.UsuarioDetailsService;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
// 	@Autowired
// 	@Qualifier("UsuarioDetailsService")
// 	private UsuarioDetailsService usuarioDetailsService;
	
// 	@Autowired
// 	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
// 		auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
// 	}
	
// 	@Bean
// 	public PasswordEncoder passwordEncoder() {
// 		return new BCryptPasswordEncoder();
// 	}

// 	// @Override
// 	// protected void configure(HttpSecurity http) throws Exception {
// 	// 	http.csrf().disable()
// 	// 	.authorizeRequests().antMatchers("/api**").permitAll()
// 	// 	.antMatchers("/login*").permitAll()
// 	// 	.antMatchers("/api/**").authenticated()
// 	// 	.and()
// 	// 	.formLogin()
// 	// 	.loginPage("/login")
// 	// 	.defaultSuccessUrl("/api/", true)
// 	// 	.permitAll()
// 	// 	.and()
// 	// 	.logout().permitAll();
// 	// }

// protected void configure(HttpSecurity http) throws Exception {
// 	http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
// 	}
// }