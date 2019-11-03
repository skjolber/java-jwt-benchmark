package com.github.skjolber.jwt;

import org.openjdk.jmh.annotations.Benchmark;

import com.auth0.jwt.interfaces.DecodedJWT;

import io.fusionauth.jwt.domain.JWT;

public class JwtParseBenchmark {

	@Benchmark
    public JWT fusionauth_parse(BenchmarkState state) throws Exception {
		return state.getFusionAuthJsonWebTokenVerifier().parseToken(state.getToken());
    }

	@Benchmark
    public DecodedJWT auth0_parse(BenchmarkState state) {
    	return state.getAuth0TokenVerifier().parseToken(state.getToken());
    }

}
