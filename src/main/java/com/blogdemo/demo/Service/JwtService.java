package com.blogdemo.demo.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blogdemo.demo.Entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;

    private static final String USER_NAME = "username";

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException{
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(User user){
       return JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .sign(algorithm);
    }

    public String getUserName(String token){
        DecodedJWT decodedJwt = JWT.require(algorithm).
                withIssuer(issuer).
                build().
                verify(token);
        return decodedJwt.getClaim(USER_NAME).asString();

    }
}
