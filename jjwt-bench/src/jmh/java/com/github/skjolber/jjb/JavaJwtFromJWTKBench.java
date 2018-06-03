package com.github.skjolber.jjb;

import org.openjdk.jmh.annotations.Benchmark;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JavaJwtFromJWTKBench {

	/*
	@Benchmark
    public Jws<Claims> parse(JwtState state) {
		return state.getParser().parseClaimsJws(state.getToken());
    }
    */

    @Benchmark
    public Jws<Claims> validate(JwtState state) {
		return state.getVerifier().parseClaimsJws(state.getToken());
    }
    
    @Benchmark
    public String claim(JwtState state) {
    	return state.getVerifier().parseClaimsJws(state.getToken()).getBody().get("test", String.class);
    }
}
