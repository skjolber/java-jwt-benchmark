package com.github.skjolber.jjb;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.interfaces.RSAKeyProvider;

public class KeyProvider implements RSAKeyProvider {

	private final KeyPair keyPair;
	
	public KeyProvider(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	@Override
	public RSAPublicKey getPublicKeyById(String keyId) {
		return (RSAPublicKey) keyPair.getPublic();
	}

    @Override
    public RSAPrivateKey getPrivateKey() {
        throw new RuntimeException();
    }

    @Override
    public String getPrivateKeyId() {
        throw new RuntimeException();
    }

}
