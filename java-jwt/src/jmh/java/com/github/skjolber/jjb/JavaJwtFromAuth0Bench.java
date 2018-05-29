package com.github.skjolber.jjb;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JavaJwtFromAuth0Bench {

	@Benchmark
    public DecodedJWT parse(JwtState state) {
    	return JWT.decode(state.getToken());
    }

    @Benchmark
    public DecodedJWT validate(JwtState state) {
    	return state.getDecoder().verifyAndDecodeToken(state.getToken());
    }
    
    @Benchmark
    public String claim(JwtState state) {
    	return state.getDecoder().verifyAndDecodeToken(state.getToken()).getClaim("test").asString();
    }
}
