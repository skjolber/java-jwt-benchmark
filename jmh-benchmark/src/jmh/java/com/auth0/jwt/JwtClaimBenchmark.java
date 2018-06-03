package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.JWTDecoder;
import com.auth0.jwt.JavaJwtState;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.skjolber.jjb.JjwtState;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtClaimBenchmark {

    @Benchmark
    public String jjwt_claim(JjwtState state) {
    	return state.getVerifier().parseClaimsJws(state.getToken()).getBody().get("test", String.class);
    }
    
    @Benchmark
    public String auth0_claim(JavaJwtState state) {
    	return state.getVerifier().verify(state.getToken()).getClaim("test").asString();
    }    
}
