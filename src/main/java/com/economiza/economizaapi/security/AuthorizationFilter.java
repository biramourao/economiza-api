package com.economiza.economizaapi.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.economiza.economizaapi.constant.SecurityConstants;
import com.economiza.economizaapi.resource.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;

public class AuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (jwt == null || !jwt.startsWith(SecurityConstants.JWT_PROVIDER)) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityConstants.JWT_INVALID_TOKEN_MSG,
					new Date());
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			writer.write(apiErrorString);

			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			return;
		}

		jwt = jwt.replace(SecurityConstants.JWT_PROVIDER, "");
		try {
			Claims clains = new JwtManager().parseToken(jwt);
			String email = clains.getSubject();
			List<String> roles = (List<String>) clains.get(SecurityConstants.JWT_ROLE_KEY);

			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			roles.forEach(role -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(role));
			});

			Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), new Date());
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			writer.write(apiErrorString);

			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			return;

		}

		filterChain.doFilter(request, response);
	}

}
