package com.github.skjolber.jwt;

import org.openjdk.jmh.annotations.Benchmark;

public abstract class JwtClaimBenchmark {

    @Benchmark
    public Object nimbus_claim(BenchmarkState state) throws Exception {
        return state.getNimbusTokenVerifier().verifyJsonWebToken(state.getToken()).getClaim("test");
    }

    @Benchmark
    public Object jjwt_claim(BenchmarkState state) throws Exception {
        return state.getJavaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken()).getBody().get("test", String.class);
    }

    @Benchmark
    public Object auth0_claim(BenchmarkState state) throws Exception {
        return state.getAuth0TokenVerifier().verifyJsonWebToken(state.getToken()).getClaim("test");
    }

    /*
    @Benchmark
    public Object okta_claim(BenchmarkState state) throws Exception {
        return state.getOktaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken()).getClaims().get("test");
    }
     */

    @Benchmark
    public Object fusionauth_claim(BenchmarkState state) throws Exception {
        return state.getFusionAuthJsonWebTokenVerifier().verifyJsonWebToken(state.getToken()).getObject("test");
    }


}
