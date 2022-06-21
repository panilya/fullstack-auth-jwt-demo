package com.panilya.authappserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetails userDetails;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetails userDetails) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetails = userDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtTokenProvider.validate(getJwtTokenFromRequest(authorizationHeader))) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtTokenProvider.getUsername(getJwtTokenFromRequest(authorizationHeader));
        UserDetails loadedUser = userDetails.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loadedUser.getUsername(), null, loadedUser.getAuthorities());
        authenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String getJwtTokenFromRequest(String authenticationHeader) {
        if (StringUtils.hasText(authenticationHeader) && authenticationHeader.startsWith("Bearer ")) {
            return authenticationHeader.substring(7);
        }
        return null;
    }
}
