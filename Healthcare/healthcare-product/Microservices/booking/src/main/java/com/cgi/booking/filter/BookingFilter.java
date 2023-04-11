package com.cgi.booking.filter;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookingFilter extends GenericFilter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse httpres=(HttpServletResponse) response;
		
		httpres.setHeader("Access-Control-Allow-Origin", "*");
		httpres.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");
		httpres.setHeader("Access-Control-Allow-Crendentials", "true");
		httpres.setHeader("Access-Control-Allow-Headers","*");
		
		if (httprequest.getMethod().equals(HttpMethod.OPTIONS.name())){
			chain.doFilter(httprequest, httpres);
		}
		else{
			 String authheader= httprequest.getHeader("Authorization");
			if(authheader == null)
			{
				httpres.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is missing.");
				return;
			}
			String mytoken = authheader.substring(7);
			
			try{
				byte[] secret ="Screepalin21051992".getBytes();
				JwtParser jwtparser=Jwts.parser().setSigningKey(secret);
				Jwt jwtobj=jwtparser.parse(mytoken);
				Claims claim= (Claims) jwtobj.getBody();
				
				String role = (String) claim.get("Role");
				System.out.println(role);
				if (httprequest.getRequestURI().contains("/update") && (!"serviceProvider".equalsIgnoreCase(role) && !"admin".equalsIgnoreCase(role))){
					httpres.sendError(HttpServletResponse.SC_FORBIDDEN, "Access restricted to service providers and admin.");
					return;
				}
			}
			catch(SignatureException sign){
				httpres.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid signature.");
				return;
			}
			catch(MalformedJwtException malforn){
				httpres.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
				return;
			}catch(Exception e) {
				httpres.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
				return;
			}
		}
		chain.doFilter(httprequest, httpres);		
		
	}


}
