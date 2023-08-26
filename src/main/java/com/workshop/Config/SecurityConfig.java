package com.workshop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.workshop.Repo.UserServiceRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	private UserServiceRepo service;
	
	public SecurityConfig(@Lazy UserServiceRepo service) {
		this.service = service;
	}
	
	  @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  @Bean
		@Primary
	  AuthenticationManagerBuilder authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(authenticationProvider());
		  return auth;
	  }
	  
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
		  DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		  auth.setUserDetailsService(service);
		  auth.setPasswordEncoder(passwordEncoder());
		  return auth;
	  }
	  
	  
	
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		  http
          .authorizeHttpRequests()
              .requestMatchers("/resources/**", "/registration", "/about","/forgot-pass","/change-pass","/register","/","/cabpage","/book").permitAll()
              .requestMatchers("/admin/**").hasAuthority("ADMIN")
              .requestMatchers("/user/**").hasAuthority("USER")
              .requestMatchers("/vendor/**").hasAuthority("VENDOR")
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .permitAll()
              .successHandler(roleBasedAuthenticationSuccessHandler())
               .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login?logout")

              .permitAll()
              .and()
              .csrf().disable();

	    	
			return http.build();
		  
	  }
	  
	   @Bean
	    public RoleBasedAuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
	        RoleBasedAuthenticationSuccessHandler successHandler = new RoleBasedAuthenticationSuccessHandler();
	        successHandler.setAdminTargetUrl("/admin/dashboard");
	        successHandler.setDoctorTargetUrl("/user/cabinfo");
            successHandler.setPatientTargetUrl("/vendor/dashboard");
	        return successHandler;
	    }

}
