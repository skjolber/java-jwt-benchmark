package com.github.skjolber.bench.okta;

import java.security.Key;
import java.security.KeyPair;
import java.time.Duration;

import com.okta.jwt.Jwt;
import com.okta.jwt.impl.jjwt.JjwtAccessTokenVerifier;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;

public class OktaJsonWebTokenVerifier {

	private final JjwtAccessTokenVerifier verifier;

	public OktaJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
	    
	    SigningKeyResolver resolver = new SigningKeyResolver() {
            
            @Override
            public Key resolveSigningKey(JwsHeader header, String plaintext) {
                return keyPair.getPrivate();
            }
            
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                return keyPair.getPrivate();
            }
        };
        verifier = new JjwtAccessTokenVerifier(issuer, audience, Duration.ZERO, resolver);
	} 

	public Jwt verifyJsonWebToken(String token) throws Exception {
		return verifier.decode(token);
	}

}
