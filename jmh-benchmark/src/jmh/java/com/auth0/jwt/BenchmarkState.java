package com.auth0.jwt;

import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.skjolber.bench.jjwt.JavaJsonWebTokenVerifier;
import com.github.skjolber.bench.okta.OktaJsonWebTokenVerifier;
import com.github.skjolber.bench.utils.JsonWebTokenGenerator;

@State(Scope.Thread)
public class BenchmarkState {

	private String token;

	private OktaJsonWebTokenVerifier oktaJsonWebTokenVerifier;
	private JavaJsonWebTokenVerifier javaJsonWebTokenVerifier;
	private Auth0TokenVerifier auth0TokenVerifier;
	
	@Setup(Level.Trial)
	public void init() throws Exception {
        JsonWebTokenGenerator generator = new JsonWebTokenGenerator();
        
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        
        String issuer = "https://test";
        String audience = "https://audience";
        
        token = generator.createJsonWebToken(map, issuer, audience);

        oktaJsonWebTokenVerifier = new OktaJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);
        javaJsonWebTokenVerifier = new JavaJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);
        auth0TokenVerifier = new Auth0TokenVerifier(generator.getKeyPair(), issuer, audience);
	}
	
	public Auth0TokenVerifier getAuth0TokenVerifier() {
		return auth0TokenVerifier;
	}
	
	public JavaJsonWebTokenVerifier getJavaJsonWebTokenVerifier() {
		return javaJsonWebTokenVerifier;
	}
	
	public OktaJsonWebTokenVerifier getOktaJsonWebTokenVerifier() {
		return oktaJsonWebTokenVerifier;
	}
	
	public String getToken() {
		return token;
	}
}
