package com.github.skjolber.bench.jjwt;

import java.security.KeyPair;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JavaJsonWebTokenVerifier implements JsonWebTokenVerifier<Jws<Claims>> {

	private final JwtParser verifier;

	public JavaJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
        verifier = Jwts.parser()
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
