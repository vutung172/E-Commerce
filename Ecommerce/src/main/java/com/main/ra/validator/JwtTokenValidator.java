package com.main.ra.validator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenValidator {
    @Value("${JwtToken.expired-time}")
    private long EXPIRED;
    @Value("${JwtToken.key}")
    private String JWT_KEY;

    public String generateToken(UserDetails userDetails){
        Date now = new Date();
        Date expired = new Date(now.getTime() + EXPIRED);
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, generateKey(JWT_KEY))
                .compact();
        return token;
    }

    public String getUserName(String token){
        String userName = Jwts.parser()
                .setSigningKey(generateKey(JWT_KEY))
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }

    public boolean isJwtToken(String token){
        try{
            Jwts
                    .parser()
                    .setSigningKey(generateKey(JWT_KEY))
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isNonExpiredToken(String token){
        try{
            if (isJwtToken(token)){
                long nonExpired = Jwts
                        .parser()
                        .setSigningKey(generateKey(JWT_KEY))
                        .parseClaimsJws(token).getBody().getExpiration().getTime();
                return nonExpired == EXPIRED || nonExpired < EXPIRED;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    private String generateKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = digest.digest(key.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(hashedBytes, "ASE");
            return base64Key(secretKeySpec.getEncoded());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String base64Key(byte[] keys){
        String base64Key = Base64.getEncoder().encodeToString(keys);
        System.out.println("SECRET_KEY: " + base64Key);
        return base64Key;
    }
}
