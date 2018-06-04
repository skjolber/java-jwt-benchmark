package com.github.skjolber.bench.okta;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.okta.jwt.Jwt;
import com.okta.jwt.impl.NimbusJwtVerifier;
import com.okta.jwt.impl.OktaJWTClaimsVerifier;

public class OktaJsonWebTokenVerifier implements JsonWebTokenVerifier<Jwt> {

	private final NimbusJwtVerifier verifier;

	public OktaJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) {
        JWK key = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey(keyPair.getPrivate()).build();
		JWKSet jwkSet = new JWKSet(key);
		JWKSource keySource = new ImmutableJWKSet<>(jwkSet);

        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();

        // The expected JWS algorithm of the access tokens (agreed out-of-band)
        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;

        // Configure the JWT processor with a key selector to feed matching public
        // RSA keys sourced from the JWK set URL
        JWSKeySelector keySelector = new JWSVerificationKeySelector(expectedJWSAlg, keySource);
        
		jwtProcessor.setJWSKeySelector(keySelector);
        jwtProcessor.setJWTClaimsSetVerifier(new OktaJWTClaimsVerifier(issuer, audience, null));

        verifier = new NimbusJwtVerifier(jwtProcessor);
	} 
	
	@Override
	public Jwt verifyJsonWebToken(String token) throws Exception {
		return verifier.decodeAccessToken(token);
	}

}
