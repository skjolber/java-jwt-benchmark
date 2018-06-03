package com.github.skjolber.jjb;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@State(Scope.Thread)
public class JjwtState {
	
	public String token;
	public JwtParser verifier;
	public JwtParser parser;
	
	@Setup(Level.Trial)
	public void init() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        
        KeyPair keyPair = keyGen.generateKeyPair();
        
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        token = generateTokenJavaJWT(keyPair, map, "https://test");
        
        parser = Jwts.parser();
        verifier = Jwts.parser().setSigningKeyResolver(new KeyProvider(keyPair));
	}
	
	public String getToken() {
		return token;
	}

	public JwtParser getParser() {
		return parser;
	}
	
	public JwtParser getVerifier() {
		return verifier;
	}

    public static String generateTokenJavaJWT(KeyPair keyPair, Map<String, Object> keys, String issuer) throws Exception {
        
        JwtBuilder builder = Jwts.builder()
          .setIssuer(issuer)
          .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
          ;
        
        for (Map.Entry<String, Object> entry : keys.entrySet()) {
            builder.claim(entry.getKey(), entry.getValue());
        }
        
        return builder.compact();
    }

}