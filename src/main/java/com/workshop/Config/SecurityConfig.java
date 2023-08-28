package com.workshop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper, AuthenticationConfiguration authenticationConfiguration) throws Exception {

	        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
	        jsonUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login");
	        jsonUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
	        jsonUsernamePasswordAuthenticationFilter.setPostOnly(true);
	        jsonUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(((request, response, authentication) -> {
	            response.setStatus(200);
	            response.getWriter().write("OK");
	        }));
	        jsonUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler((request, response, exception) -> {
	            response.setStatus(401);
	            response.getWriter().write("AUTHENTICATION_FAILED");
	        });

	        return jsonUsernamePasswordAuthenticationFilter;
	    }
	  
	
	  
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

	       
		  return httpSecurity
	                .cors().and()
	                .csrf().disable()
	                .securityContext().requireExplicitSave(false)
	                .securityContextRepository(new HttpSessionSecurityContextRepository())
	                .and()
	                .logout()
	                .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK), request -> request.getServletPath().equals("/logout"))
	                .and()
	                .exceptionHandling()
	                .accessDeniedHandler(new AccessDeniedHandlerImpl())
	                .authenticationEntryPoint((request, response, authException) -> {
	                    response.setStatus(401);
	                    response.getWriter().write("BAD_AUTHENTICATION");
	                })
	                .and()
	                .authorizeHttpRequests()
	                .requestMatchers(HttpMethod.POST, "/login","register","/book").permitAll()
	                .requestMatchers(HttpMethod.GET,"/cabinfo").permitAll()

	                .requestMatchers("/top-secret").hasAuthority("ACCESS_TOP_SECRET")
	                .requestMatchers("/secret").hasAuthority("ACCESS_SECRET")
	                .requestMatchers("/admin/**").hasAuthority("ADMIN")
	                .requestMatchers("/user/**").hasAuthority("USER") 

	                .anyRequest().authenticated()
	                .and()
	                .build();

	    }
	   @Bean
	    public RoleBasedAuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
	        RoleBasedAuthenticationSuccessHandler successHandler = new RoleBasedAuthenticationSuccessHandler();
	       // successHandler.setAdminTargetUrl("/admin/dashboard");
	       // successHandler.setDoctorTargetUrl("/user/cabinfo");
            successHandler.setPatientTargetUrl("/vendor/dashboard");
	        return successHandler;
	    }

}
