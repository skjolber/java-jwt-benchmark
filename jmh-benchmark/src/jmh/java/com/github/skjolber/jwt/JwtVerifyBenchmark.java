package com.github.skjolber.jwt;

import org.openjdk.jmh.annotations.Benchmark;

public class JwtVerifyBenchmark {
/*
    @Benchmark
    public Object nimbus_verify(BenchmarkState state) throws Exception {
        return state.getNimbusTokenVerifier().verifyJsonWebToken(state.getToken());
    }

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

    @Benchmark
    public Object fusionauth_verify(BenchmarkState state) throws Exception {
        return state.getFusionAuthJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }

    @Benchmark
    public Object fusionauth2_verify(BenchmarkState state) throws Exception {
        return state.getFusionAuthJsonWebTokenVerifier2().verifyJsonWebToken(state.getToken());
    }
*/

    @Benchmark
    public Object jjwt_verify(BenchmarkState state) throws Exception {
        return state.getJavaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }

    @Benchmark
    public Object jjwt_verify2(BenchmarkState state) throws Exception {
        return state.getJavaJsonWebTokenVerifier2().verifyJsonWebToken(state.getToken());
    }

}
