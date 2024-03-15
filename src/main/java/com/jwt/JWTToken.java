package com.jwt;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTToken {          												//SecretKey is used to validate token
	private static final  SecretKey secretKey= Keys.secretKeyFor(SignatureAlgorithm.HS256);     //secretKeyFor() method is used for returns new secretkey
	private static final long Expiration_Time=3600000; 							//Set Expiration Time for 1hour in milliseconds
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();  //HashMap is used to store key,value pairs
        map.put("userId","1234");  								//Private Claims or Custom Data  Claims-Where each Key is claims
        map.put("userName", "Lokesh");
        String jwtToken=generateJwtToken(map);
        System.out.println(jwtToken);
    }
	private static String generateJwtToken(Map<String, String> map) {   // Which is used to generate a token
		Date now = new Date();   										// it returns Current Date
        Date expiration = new Date(now.getTime() + Expiration_Time);
        return Jwts.builder()  										//builder() Method which is used to calls setofclaims,headers,other properties
                .setClaims(map)  									//Registerd Claims
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();  // Compact() Method which is used to generate Final JWT String
	}
}
