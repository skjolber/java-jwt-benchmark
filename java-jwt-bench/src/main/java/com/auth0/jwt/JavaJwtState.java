package com.auth0.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;

@State(Scope.Thread)
public class JavaJwtState {
	
	public String token;
	public JWTVerifier verifier;
	public JWTParser parser = new JWTParser();
	
	@Setup(Level.Trial)
	public void init() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        
        KeyPair keyPair = keyGen.generateKeyPair();
        verifier = JWT.require(Algorithm.RSA256(new KeyProvider(keyPair))).build();
        
        JWTEncoder encoder = new JWTEncoder(keyPair);
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        token = encoder.generateTokenAuth0(map, "https://test");
        
        parser = new JWTParser();
	}
	
	public String getToken() {
		return token;
	}

	public JWTParser getParser() {
		return parser;
	}
	
	public JWTVerifier getVerifier() {
		return verifier;
	}
}