package com.maxkucher.note_taking.security.filters;

import com.maxkucher.note_taking.security.SecurityConstants;
import com.maxkucher.note_taking.security.SecurityUserDetails;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/authorize");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authResult.getPrincipal();
        String token = Jwts
                .builder()
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.TOKEN_SECRET.getBytes()), SignatureAlgorithm.HS512)
                .setSubject(securityUserDetails.getUsername())
                .setExpiration(new Date(new Date().getTime() + 28800000))
                .compact();
        response.addHeader(SecurityConstants.AUTHORIZATION_HEADER_NAME, SecurityConstants.TOKEN_PREFIX + token);
    }
}
