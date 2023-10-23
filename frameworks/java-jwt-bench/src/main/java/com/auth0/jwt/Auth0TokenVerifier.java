package com.auth0.jwt;

import java.security.KeyPair;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class Auth0TokenVerifier {

	public static Auth0TokenVerifier newInstance(KeyPair keyPair, String issuer, String audience) {
		JWTVerifier build = JWT
				.require(Algorithm.RSA256(new KeyProvider(keyPair)))
				.withIssuer(issuer)
				.withAudience(audience)
				.build();

		return new Auth0TokenVerifier(build);
	}

	private final JWTVerifier verifier;
	private final JWT jwt;

	public Auth0TokenVerifier(JWTVerifier jwtVerifier) {
		this.verifier = jwtVerifier;
        this.jwt = new JWT();
	}

	public DecodedJWT verifyJsonWebToken(String token) throws Exception {
		return verifier.verify(token);
	}

	public DecodedJWT parseToken(String token) {
		return jwt.decodeJwt(token);
	}

}
