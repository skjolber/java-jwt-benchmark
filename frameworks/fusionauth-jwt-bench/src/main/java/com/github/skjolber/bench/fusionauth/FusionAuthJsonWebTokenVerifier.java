package com.github.skjolber.bench.fusionauth;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.JWTDecoder;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;

public class FusionAuthJsonWebTokenVerifier {

	public static FusionAuthJsonWebTokenVerifier newInstance(KeyPair keyPair, String issuer, String audience) {
		RSAVerifier rsaVerifier = RSAVerifier.newVerifier((RSAPublicKey) keyPair.getPublic());

		return new FusionAuthJsonWebTokenVerifier(issuer, audience, rsaVerifier);
	}

	private final RSAVerifier verifier;
	private final JWTDecoder decoder;
	private final String issuer;
	private final String audience;

	public FusionAuthJsonWebTokenVerifier(String issuer, String audience, RSAVerifier verifier) {
		this.issuer = issuer;
		this.audience = audience;
		this.verifier = verifier;
		this.decoder = JWT.getDecoder();
	} 

	public JWT verifyJsonWebToken(String token) {
		JWT jwt =  decoder.decode(token, verifier);

		// lets add some claim verification here; the other implementations do this already
		if(!issuer.equals(jwt.getString("iss"))) {
			throw new InvalidJWTException("Unexpected issuer");
		}

		if(!audience.equals(jwt.getString("aud"))) {
			throw new InvalidJWTException("Unexpected audience");
		}

		return jwt;
	}

	public JWT parseToken(String token) throws Exception {
		return JWTUtils.decodePayload(token);
	}
}
