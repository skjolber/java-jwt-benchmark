package com.auth0.jwt;

import org.openjdk.jmh.annotations.Benchmark;

public class JwtVerifyBenchmark {

    @Benchmark
    public Object jjwt_verify(BenchmarkState state) throws Exception {
		return state.getJavaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }
    
    @Benchmark
    public Object auth0_verify(BenchmarkState state) throws Exception {
    	return state.getAuth0TokenVerifier().verifyJsonWebToken(state.getToken());
    }
    
    @Benchmark
    public Object okta_verify(BenchmarkState state) throws Exception {
    	return state.getOktaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }
}
