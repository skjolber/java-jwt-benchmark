package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.JWTDecoder;
import com.auth0.jwt.JavaJwtState;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.skjolber.jjb.JjwtState;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtParseBenchmark {

	/*
	@Benchmark
    public Jws<Claims> parse(JwtState state) {
		return state.getParser().parseClaimsJws(state.getToken());
    }
    */

	@Benchmark
    public DecodedJWT auth0_parse(JavaJwtState state) {
		return new JWTDecoder(state.getToken());
		//return new JWTDecoder(state.getParser(), state.getToken());
    }

}
