package com.auth0.jwt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTEncoder {

	private final KeyPair keyPair; 
	
    public JWTEncoder(KeyPair keyPair) {
		super();
		this.keyPair = keyPair;
	}

	public String generateTokenAuth0(Map<String, Object> keys, String issuer, String audience) throws Exception {
        Builder builder = JWT.create()
            .withKeyId("12345678")
            .withAudience(audience)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .withIssuer(issuer);

        for (Entry<String, Object> entry : keys.entrySet()) {
            Object value = entry.getValue();
            if(value instanceof String[]) {
                builder.withArrayClaim(entry.getKey(), (String[])entry.getValue());
            } else if(value instanceof String) {
                builder.withClaim(entry.getKey(), (String)entry.getValue());
            } else if(value instanceof Long) {
                builder.withClaim(entry.getKey(), (Long)entry.getValue());
            } else {
                throw new IllegalArgumentException(value.getClass().toString());
            }
        }
        
        return builder.sign(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()));
    }

}
