package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.JWTDecoder;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JavaJwtFromAuth0Bench {

	@Benchmark
    public DecodedJWT parse(JwtState state) {
		return new JWTDecoder(state.getToken());
		//return new JWTDecoder(state.getParser(), state.getToken());
    }

    @Benchmark
    public DecodedJWT validate(JwtState state) {
    	return state.getVerifier().verify(state.getToken());
    }
    
    @Benchmark
    public String claim(JwtState state) {
    	return state.getVerifier().verify(state.getToken()).getClaim("test").asString();
    }
}
