package com.jwt;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Jwt1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		byte[] keyBytes = secretKey.getEncoded();
		System.out.println("Secret Key: " + bytesToHex(keyBytes));
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02X", b));
		}
		return result.toString();
	}

}
