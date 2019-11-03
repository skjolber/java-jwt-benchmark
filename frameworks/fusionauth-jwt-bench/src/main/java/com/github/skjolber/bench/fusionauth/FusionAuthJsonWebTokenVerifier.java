package com.github.skjolber.bench.fusionauth;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.JWTDecoder;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.Algorithm;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;

public class FusionAuthJsonWebTokenVerifier implements JsonWebTokenVerifier<JWT> {

	private final RSAVerifier verifier;
	private final JWTDecoder decoder;
	private final String issuer;
	private final String audience;

	public FusionAuthJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
		this.issuer = issuer;
		this.audience = audience;
		
		this.verifier = RSAVerifier.newVerifier((RSAPublicKey) keyPair.getPublic());
		
		this.decoder = JWT.getDecoder();
	} 
	
	@Override
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
