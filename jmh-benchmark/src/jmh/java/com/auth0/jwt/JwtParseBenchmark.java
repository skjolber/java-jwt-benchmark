package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtParseBenchmark {

	/*
	@Benchmark
    public Jws<Claims> parse(JwtState state) {
		return state.getParser().parseClaimsJws(state.getToken());
    }
    */

	@Benchmark
    public DecodedJWT auth0_parse(BenchmarkState state) {
		return new JWTDecoder(state.getToken());
		//return new JWTDecoder(state.getParser(), state.getToken());
    }

}
