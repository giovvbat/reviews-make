package com.giovanna.reviewsmake.security;

import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*secure free endpoints against invalid jwt-tokens*/
        if (freeEndpoints(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = this.resolveToken(request);
        String login = tokenService.verifyToken(token);

        /*sucess while verifying token*/
        if (login != null) {
            Optional<UserModel> user = userRepository.findByUsername(login);

            if (user.isEmpty()) {
                throw new RuntimeException("Invalid username!");
            }

            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }

        /*no token provided!*/
        return null;
    }

    private boolean freeEndpoints(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/users/login") ||
        request.getRequestURI().startsWith("/users/register");
    }
}