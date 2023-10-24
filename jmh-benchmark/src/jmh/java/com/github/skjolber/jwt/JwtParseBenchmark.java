package com.github.skjolber.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.fusionauth.jwt.domain.JWT;
import org.openjdk.jmh.annotations.Benchmark;

import java.text.ParseException;

public abstract class JwtParseBenchmark {

    @Benchmark
    public com.nimbusds.jwt.JWT nimbus_parse(BenchmarkState state) throws ParseException {
        return state.getNimbusTokenVerifier().parseToken(state.getToken());
    }
    
    @Benchmark
    public JWT fusionauth_parse(BenchmarkState state) throws Exception {
        return state.getFusionAuthJsonWebTokenVerifier().parseToken(state.getToken());
    }

    @Benchmark
    public DecodedJWT auth0_parse(BenchmarkState state) {
        return state.getAuth0TokenVerifier().parseToken(state.getToken());
    }

}
