package com.github.skjolber.jwt;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.RunnerException;


import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1, jvmArgsPrepend = "-XX:-RestrictContended")
@Warmup(iterations = 1, time = 15, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 1, time = 30, timeUnit = TimeUnit.SECONDS)
public class JwtVerifyBenchmark {

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

    /*
    @Benchmark
    public Object okta_verify(BenchmarkState state) throws Exception {
        return state.getOktaJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }
    */

    @Benchmark
    public Object fusionauth_verify(BenchmarkState state) throws Exception {
        return state.getFusionAuthJsonWebTokenVerifier().verifyJsonWebToken(state.getToken());
    }

    @Benchmark
    public Object base_verify_bc(BenchmarkState state) throws Exception {
        return state.getBaseBouncyCastleJwtVerifier().verifyJsonWebToken(state.getToken());
    }

    @Benchmark
    public Object base_verify_jdk(BenchmarkState state) throws Exception {
        return state.getBaseJdkJwtVerifier().verifyJsonWebToken(state.getToken());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JwtVerifyBenchmark.class.getSimpleName())
                .mode(Mode.Throughput)

                .forks(1)
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(10))
                .timeout(TimeValue.seconds(10))

                .build();

        new Runner(opt).run();
    }
}
