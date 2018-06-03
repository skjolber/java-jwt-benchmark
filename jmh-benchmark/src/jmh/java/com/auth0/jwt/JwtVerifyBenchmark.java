package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.JWTDecoder;
import com.auth0.jwt.JavaJwtState;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.skjolber.jjb.JjwtState;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtVerifyBenchmark {

    @Benchmark
    public Jws<Claims> jjwt_verify(JjwtState state) {
		return state.getVerifier().parseClaimsJws(state.getToken());
    }
    @Benchmark
    public DecodedJWT auth0_verify(JavaJwtState state) {
    	return state.getVerifier().verify(state.getToken());
    }
}
