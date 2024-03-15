package com.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RS512Example {
    public static void main(String[] args) {
        // Generate an RSA key pair
        KeyPair keyPair = generateKeyPair();

        // Get the private and public keys from the key pair
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Create a sample JWT using RS512 algorithm
        String jwtToken = createJwtToken(privateKey);

        // Verify the JWT using the public key
        boolean isValid = verifyJwtToken(jwtToken, publicKey);

        System.out.println("JWT Token: " + jwtToken);
        System.out.println("JWT Token is valid: " + isValid);
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // You can choose the key size
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String createJwtToken(PrivateKey privateKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", "12345");
        claims.put("userName", "Lokesh");

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    private static boolean verifyJwtToken(String jwtToken, PublicKey publicKey) {
        try {
            Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
