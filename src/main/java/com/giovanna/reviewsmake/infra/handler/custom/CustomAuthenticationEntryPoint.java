package com.giovanna.reviewsmake.infra.handler.custom;

import com.giovanna.reviewsmake.infra.security.SecurityFilter;
import com.giovanna.reviewsmake.infra.security.TokenService;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityFilter securityFilter;
    private final ArrayList<String> freeEndpoints = new ArrayList<>();

    @PostConstruct
    private void init() {
        freeEndpoints.add("users/redefine-password");
        freeEndpoints.add("users/login");
        freeEndpoints.add("users/register");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        /*authentication made, error was unexpected*/
        if (validateAuthentication(request)) {
            writeResponse(response, "an expected error ocurred", HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }

        /*authentication failed, endpoint access is unauthorized*/
        writeResponse(response, "resource requires user authentication", HttpStatus.UNAUTHORIZED);
    }

    /*auxiliary method for exception handling*/
    public boolean validateAuthentication(HttpServletRequest request) {
        if (securityFilter.isFilterSkippable(request, freeEndpoints)) {
            return true;
        }

        String token = securityFilter.resolveToken(request);

        if (token != null) {
            String login = tokenService.verifyToken(token);

            if (login != null) {
                Optional<UserModel> user = userRepository.findByUsername(login);

                /*checks if authentication was made correctly*/
                return user.isPresent();
            }
        }

        /*error in authentication*/
        return false;
    }

    /*auxiliary method for writing in json-api-response*/
    private void writeResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status.value());
        response.getOutputStream().println("{ \"status\": " + status.toString() + ",\n" + "\"error\": \"" + message + "\" }");
    }
}