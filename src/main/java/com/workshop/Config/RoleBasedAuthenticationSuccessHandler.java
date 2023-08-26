package com.workshop.Config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	 private String adminTargetUrl;
	    private String userTargetUrl;
	    private String vendorTargetUrl;
	    
	    
	    public void setAdminTargetUrl(String adminTargetUrl) {
	        this.adminTargetUrl = adminTargetUrl;
	    }

	    public void setDoctorTargetUrl(String userTargetUrl) {
	        this.userTargetUrl = userTargetUrl;
	    }

	    public void setPatientTargetUrl(String vendorTargetUrl) {
	        this.vendorTargetUrl = vendorTargetUrl;
	    }

	    
	   @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	            response.sendRedirect(request.getContextPath() + adminTargetUrl);
	        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
	            response.sendRedirect(request.getContextPath() + userTargetUrl);
	        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("VENDOR"))) {
	            response.sendRedirect(request.getContextPath() + vendorTargetUrl);
	        } else {
	            throw new IllegalStateException();
	        }
		
	}


}
