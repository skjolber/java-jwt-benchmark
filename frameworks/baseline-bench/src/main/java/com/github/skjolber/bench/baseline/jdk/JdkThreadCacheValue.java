package com.github.skjolber.bench.baseline.jdk;

import com.github.skjolber.bench.baseline.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;

public class JdkThreadCacheValue {

	private int payloadLength;
	private final int payloadOffset;

	private final byte[] buffer = new byte[256];

	private final Signature signature;

	public JdkThreadCacheValue(int headerSize, int payloadLength, Signature signature) throws NoSuchAlgorithmException, InvalidKeyException {
		this.payloadOffset = headerSize;
		this.payloadLength = payloadLength;

		this.signature = signature;
	}

	public boolean validate(byte[] jwt) throws SignatureException {
		Base64.decodeFast256(jwt, payloadOffset + payloadLength + 1, buffer);
		signature.update(jwt, 0, payloadOffset + payloadLength);
		return signature.verify(buffer);
	}
	
}
