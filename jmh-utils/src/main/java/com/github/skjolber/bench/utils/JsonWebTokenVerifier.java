package com.github.skjolber.bench.utils;

public interface JsonWebTokenVerifier <T>{

	T verifyJsonWebToken(String token) throws Exception;
}
