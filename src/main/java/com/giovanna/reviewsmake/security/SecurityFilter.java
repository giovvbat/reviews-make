//package com.giovanna.reviewsmake.security;
//
//import com.giovanna.reviewsmake.model.UserModel;
//import com.giovanna.reviewsmake.repository.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Optional;
//
//@Component
//public class SecurityFilter extends OncePerRequestFilter {
//    @Autowired
//    TokenService tokenService;
//    @Autowired
//    UserRepository userRepository;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = this.resolveToken(request);
//        String login = tokenService.verifyToken(token);
//
//        if (login != null) {
//            Optional<UserModel> user = userRepository.findByUsername(login);
//            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//
//            if (user.isEmpty()) {
//                throw new RuntimeException("Invalid username!");
//            }
//
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//
//        if (bearerToken != null) {
//            return bearerToken;
//        }
//
//        return bearerToken.replace("Bearer ", "");
//    }
//}
