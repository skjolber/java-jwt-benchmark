package com.github.skjolber.bench.jjwt;

import java.security.KeyPair;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JavaJsonWebTokenVerifier {

	private final JwtParser verifier;

	public JavaJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
        verifier = Jwts.parser()
        		.setSigningKeyResolver(new KeyProvider(keyPair))
        		.requireAudience(audience)
        		.requireIssuer(issuer)
        		;
	}

	public Jws<Claims> verifyJsonWebToken(String token) throws Exception {
		return verifier.parseClaimsJws(token);
	}

}
