package com.github.skjolber.bench.baseline;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

public class JsonWebTokenGenerator {

    public static JsonWebTokenGenerator newInstance() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        return new JsonWebTokenGenerator(keyGen.generateKeyPair());
    }

	private final KeyPair keyPair;
	
	public JsonWebTokenGenerator(KeyPair keyPair) {
        this.keyPair = keyPair;
	}
	
    public String createJsonWebToken(Map<String, Object> keys, String issuer, String audience) throws Exception {
        JwtBuilder builder = Jwts.builder()
          .setIssuer(issuer)
          .setAudience(audience)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
          .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
          ;
        
        for (Map.Entry<String, Object> entry : keys.entrySet()) {
            builder.claim(entry.getKey(), entry.getValue());
        }
        
        return builder.compact();
    }
    
    public KeyPair getKeyPair() {
		return keyPair;
	}
}
