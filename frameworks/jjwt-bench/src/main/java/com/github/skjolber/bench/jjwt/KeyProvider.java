package com.github.skjolber.bench.jjwt;

import java.security.Key;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;

public class KeyProvider implements SigningKeyResolver {

	private final KeyPair keyPair;
	
	public KeyProvider(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		return (RSAPublicKey) keyPair.getPublic();
	}

	@Override
	public Key resolveSigningKey(JwsHeader header, String plaintext) {
		return (RSAPublicKey) keyPair.getPublic();
	}

}
