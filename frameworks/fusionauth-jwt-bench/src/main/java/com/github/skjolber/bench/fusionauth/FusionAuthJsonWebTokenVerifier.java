package com.github.skjolber.bench.fusionauth;

import java.security.KeyPair;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.JWTDecoder;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.Algorithm;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import io.fusionauth.pem.domain.PEM;

public class FusionAuthJsonWebTokenVerifier implements JsonWebTokenVerifier<JWT> {

	private final static Verifier nullVerifier = new Verifier() {
		
		@Override
		public void verify(Algorithm algorithm, byte[] message, byte[] signature) {
			// noop
		}
		
		@Override
		public boolean canVerify(Algorithm algorithm) {
			return true;
		}
	};
	
	private final RSAVerifier verifier;
	private final JWTDecoder decoder;
	private final String issuer;
	private final String audience;

	public FusionAuthJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
		this.issuer = issuer;
		this.audience = audience;
		
		String encode = PEM.encode(keyPair.getPublic());
		
		decoder = JWT.getDecoder();
		verifier = RSAVerifier.newVerifier(encode);
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
		return decoder.decode(token, nullVerifier);
	}
}
