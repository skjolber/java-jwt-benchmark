package com.github.skjolber.bench.jjwt;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;

import java.security.KeyPair;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;

public class JavaJsonWebTokenVerifier2 implements JsonWebTokenVerifier<Jws<Claims>> {

	private final JwtParser verifier;

	public JavaJsonWebTokenVerifier2(KeyPair keyPair, String issuer, String audience) {
		verifier = new DefaultJwtParser()
				.setSigningKeyResolver(new KeyProvider(keyPair))
				.requireAudience(audience)
				.requireIssuer(issuer)
		;
	}

	@Override
	public Jws<Claims> verifyJsonWebToken(String token) throws Exception {
		return verifier.parseClaimsJws(token);
	}

}
