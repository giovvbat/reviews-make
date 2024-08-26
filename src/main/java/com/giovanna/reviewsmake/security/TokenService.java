//package com.giovanna.reviewsmake.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.giovanna.reviewsmake.model.UserModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Service
//public class TokenService {
//    @Value("${api.security.token.secret}")
//    private String secret;
//
//    public String generateToken(UserModel user) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            String token = JWT.create()
//                    .withIssuer("reviews-make-api")
//                    .withSubject(user.getUsername())
//                    .withExpiresAt(this.generateExpirationTime())
//                    .sign(algorithm);
//            return token;
//        } catch(JWTCreationException exception) {
//            throw new JWTCreationException("Error while creating JWT token!", exception);
//        }
//    }
//
//    public String verifyToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer("reviews-make-api")
//                    .build()
//                    .verify(token)
//                    .getSubject();
//        } catch(JWTCreationException exception) {
//            /*error while verifying token*/
//            return null;
//        }
//    }
//
//    private Instant generateExpirationTime() {
//        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
//    }
//}