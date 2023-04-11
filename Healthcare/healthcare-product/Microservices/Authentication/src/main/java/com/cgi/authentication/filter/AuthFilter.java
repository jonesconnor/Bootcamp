package com.cgi.authentication.filter;

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


public class AuthFilter extends GenericFilter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpres = (HttpServletResponse) response;
		
		// CORS control
		httpres.setHeader("Access-Control-Allow-Origin", "*");
		httpres.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
		//httpres.setHeader("Access-Control-Allow-Crendentials", "true");
		httpres.setHeader("Access-Control-Allow-Headers","*");
		
		// Check to see if client is sending a preflight request to check if the server
		// supports certain HTTP methods
		if (!httprequest.getMethod().equals(HttpMethod.DELETE.name())) {
			chain.doFilter(httprequest, httpres);
		} else {
			String authheader = httprequest.getHeader("Authorization");
			
			if ((authheader == null) || (! authheader.startsWith("Bearer"))) {
				httpres.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is missing");
				return;
			}
			
			String token = authheader.substring(7);
			
			try {
				
				byte[] secret = "Screepalin21051992".getBytes();
				
				JwtParser jwtparser = Jwts.parser().setSigningKey(secret);
				Jwt jwtobj = jwtparser.parse(token);
				Claims claim = (Claims) jwtobj.getBody();
				String role = (String) claim.get("Role");
				
				// Allow ServiceProvider to the /medicalrecords/add endpoint
				if (httprequest.getRequestURI().contains("/delete")) {
					if (!role.equals("Admin")) {
						httpres.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
						return;
					}
				}
				
			} catch (SignatureException e) {
				//throw new ServletException("Signature mismatch");
				httpres.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid signature");
				return;
			} catch (MalformedJwtException e) {
				//throw new ServletException("Token has been modified");
				httpres.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
				return;
			}
			
		}

		chain.doFilter(httprequest, httpres);
	}

}
