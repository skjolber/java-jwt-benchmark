package org.jose4j.jwt;

import java.security.KeyPair;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtContext;

public class Jose4JTokenVerifier {

	public static Jose4JTokenVerifier newInstance(KeyPair keyPair, String issuer, String audience) {
		JwtConsumer consumer = new JwtConsumerBuilder()
				.setVerificationKey(keyPair.getPublic())
				.setRequireExpirationTime()
				.setExpectedIssuer(issuer)
				.setExpectedAudience(audience)
				.build();

		return new Jose4JTokenVerifier(consumer);
	}

	private final JwtConsumer consumer;

	public Jose4JTokenVerifier(JwtConsumer consumer) {
		this.consumer = consumer;
	}

	public JwtClaims verifyJsonWebToken(String token) throws Exception {
		return consumer.processToClaims(token);
	}

	public JwtContext parseToken(String token) throws Exception {
		return consumer.process(token);
	}
}
