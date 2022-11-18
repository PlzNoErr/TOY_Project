package com.ssafy.ssafit.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SALT = "SSAFIT1234";

    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT")
                .claim(claimId, data)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }

    public void valid(String token) throws Exception {
        Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
    }
}