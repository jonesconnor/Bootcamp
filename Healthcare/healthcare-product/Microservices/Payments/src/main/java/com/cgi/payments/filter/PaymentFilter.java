package com.cgi.payments.filter;

import java.io.IOException;

import org.springframework.http.HttpMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaymentFilter extends GenericFilter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// CORS control
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
		httpResponse.setHeader("Access-Control-Allow-Crendentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Headers","*");
		
		// Check to see if client is sending a preflight request to check if the server
		// supports certain HTTP methods
		if (httpRequest.getMethod().equals(HttpMethod.OPTIONS.name()) || 
				!(httpRequest.getRequestURI().contains("/serviceProvider") || 
					httpRequest.getRequestURI().contains("/patient") ||
					httpRequest.getRequestURI().contains("/admin"))) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			String authheader = httpRequest.getHeader("Authorization");
			
			if ((authheader == null) || (! authheader.startsWith("Bearer"))) {
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is missing");
				return;
			}
			
			String token = authheader.substring(7);
			
			try {
				
				byte[] secret ="Screepalin21051992".getBytes();
				JwtParser jwtparser=Jwts.parser().setSigningKey(secret);;
				Claims claim= (Claims) jwtparser.parse(token).getBody();
				String role = (String) claim.get("Role");
				
				//define end points for service provider (admin has also access)
				if (httpRequest.getRequestURI().contains("/serviceProvider") 
						&& !(role.equals("ServiceProvider") || role.equals("Admin"))) {
					httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Must be logged as a service provider or admin");
					return;
				}
				//define end points for patient (admin has also access)
				else  if(httpRequest.getRequestURI().contains("/patient") 
						&& !(role.equals("Patient") || role.equals("Admin"))) {
					httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Must be logged as a patient or admin");
					return;
				}
				else  if(httpRequest.getRequestURI().contains("/admin") 
						&& !(role.equals("Admin"))) {
					httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Must be logged as an admin");
					return;
				}
				
			} catch (SignatureException e) {
				//throw new ServletException("Signature mismatch");
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid signature");
				return;
			} catch (MalformedJwtException e) {
				//throw new ServletException("Token has been modified");
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
				return;
			}
			catch (Exception e) {
				//throw new ServletException("Token has been modified");
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
				return;
			}
			
		}

		chain.doFilter(httpRequest, httpResponse);
	}

	
}
