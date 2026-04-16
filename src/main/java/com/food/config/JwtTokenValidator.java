package com.food.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		
	/*	 String path = request.getServletPath();

	        // 🚨 Skip JWT validation for signup & login
	        if (path.startsWith("/auth/signup") || path.startsWith("/auth/login")) {
	            filterChain.doFilter(request, response);
	            return;
	        }*/

		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
	
		//Bearer token
		if(jwt!=null && jwt.startsWith("Bearer ") ) //++++   && jwt.startsWith("Bearer ")
		{
			jwt = jwt.substring(7);
			
			try {
				
			SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
			Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
			
			String email = String.valueOf(claims.get("email"));
			String authorities = String.valueOf((claims.get("authorities")));
			
			//ROLE_CUSTOMER , ROLE_ADMIN
			List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(authorities);
			Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auth);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			}
			catch(Exception e){
				throw new BadCredentialsException("Invalid token....");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
