package com.github.skjolber.bench.jjwt;

import java.security.KeyPair;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JavaJsonWebTokenVerifier {

	public static JavaJsonWebTokenVerifier newInstance(KeyPair keyPair, String issuer, String audience) {
		JwtParser jwtParseruild = Jwts.parser()
				.setSigningKeyResolver(new KeyProvider(keyPair))
				.requireAudience(audience)
				.requireIssuer(issuer)
				.build();

		return new JavaJsonWebTokenVerifier(jwtParseruild);
	}

	private final JwtParser verifier;

	public JavaJsonWebTokenVerifier(JwtParser verifier) {
		this.verifier = verifier;
	}

	public Jws<Claims> verifyJsonWebToken(String token) throws Exception {
		return verifier.parseClaimsJws(token);
	}

}
