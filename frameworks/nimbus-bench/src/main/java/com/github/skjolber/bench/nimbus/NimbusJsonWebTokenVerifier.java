package com.github.skjolber.bench.nimbus;

import com.github.skjolber.bench.utils.JsonWebTokenVerifier;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.net.MalformedURLException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.UUID;

public class NimbusJsonWebTokenVerifier implements JsonWebTokenVerifier<JWTClaimsSet> {

	private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

	public NimbusJsonWebTokenVerifier(KeyPair keyPair, String issuer, String audience) throws JOSEException, MalformedURLException {

		// Convert to JWK format
		RSAKey rsaJWK = new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
				.privateKey((RSAPrivateKey)keyPair.getPrivate())
				.keyUse(KeyUse.SIGNATURE)
				.keyID(UUID.randomUUID().toString())
				.build();

		jwtProcessor = new DefaultJWTProcessor<>();

		JWKSource keySource = new ImmutableJWKSet(new JWKSet(rsaJWK));

		JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;

		JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(expectedJWSAlg, keySource);

		jwtProcessor.setJWSKeySelector(keySelector);
	}
	
	@Override
	public JWTClaimsSet verifyJsonWebToken(String token) throws Exception {
		return jwtProcessor.process(token, null);
	}

	public JWT parseToken(String token) throws ParseException {
		return JWTParser.parse(token);
	}
}
