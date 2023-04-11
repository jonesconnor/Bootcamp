package com.stackroute.newz.fillter;

import java.io.IOException;

import org.springframework.http.HttpMethod;

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

public class NewsFilter extends GenericFilter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		
		httpresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
		httpresponse.setHeader("Access-Control-Allow-Crendentials", "true");
		httpresponse.setHeader("Access-Control-Allow-Headers","*");
		
		if (httprequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
			chain.doFilter(httprequest, httpresponse);
		} else {
			String authheader = httprequest.getHeader("Authorization");
			
			if ((authheader == null) || (! authheader.startsWith("Bearer"))) {
				throw new ServletException("JWT Token is missing");
			}
			
			String token = authheader.substring(7);
			
			try {
				JwtParser jwtparser = Jwts.parser().setSigningKey("authkey");
				
				// validates token
				Jwt jwtobj = jwtparser.parse(token);
				
			} catch(SignatureException sign) {
				throw new ServletException("Signature Mismatch");
			} catch (MalformedJwtException malform) {
				throw new ServletException("Token has been modified");
			}

		}
		
		chain.doFilter(httprequest, httpresponse);
		
	}

}
