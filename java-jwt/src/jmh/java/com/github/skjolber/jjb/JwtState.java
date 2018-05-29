package com.github.skjolber.jjb;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JwtState {
	
	public String token;
	public JWTDecoder decoder;
	
	@Setup(Level.Trial)
	public void init() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        
        KeyPair keyPair = keyGen.generateKeyPair();
        
        decoder = new JWTDecoder(new KeyProvider(keyPair));
        JWTEncoder encoder = new JWTEncoder(keyPair);
        
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        token = encoder.generateTokenAuth0(map, "https://test");
	}
	
	public String getToken() {
		return token;
	}
	
	public JWTDecoder getDecoder() {
		return decoder;
	}
}