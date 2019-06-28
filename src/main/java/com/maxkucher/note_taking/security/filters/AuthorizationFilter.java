package com.maxkucher.note_taking.security.filters;

import com.maxkucher.note_taking.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;



public class AuthorizationFilter extends BasicAuthenticationFilter {






    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader;
        try {
            authHeader = httpServletRequest.getHeader(SecurityConstants.AUTHORIZATION_HEADER_NAME);
        }catch (Exception e){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        if (Objects.isNull(authHeader) || !authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String jwtToken = authHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.TOKEN_SECRET.getBytes())
                    .parseClaimsJws(jwtToken)
                    .getBody();

            String email = claims.getSubject();

            if (Objects.nonNull(email) && Strings.isNotBlank(email)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,
                        null,
                        null);
                SecurityContextHolder.getContext().setAuthentication(token);
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
