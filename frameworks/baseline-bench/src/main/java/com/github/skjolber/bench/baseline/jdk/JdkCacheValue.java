package com.github.skjolber.bench.baseline.jdk;

import org.bouncycastle.crypto.digests.SHA256Digest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

public class JdkCacheValue {

	private final ThreadLocal<JdkThreadCacheValue> threadLocal = new ThreadLocal<>();

	private final int payloadLength;

	private final int payloadOffset;

	private final RSAPublicKey publicKey;

	public JdkCacheValue(int payloadOffset, int payloadLength, RSAPublicKey publicKey) {
		this.payloadOffset = payloadOffset;
		this.payloadLength = payloadLength;
		this.publicKey = publicKey;
	}

	public JdkThreadCacheValue getThreadCacheValue() throws NoSuchAlgorithmException, InvalidKeyException {
		JdkThreadCacheValue jdkThreadCacheValue = threadLocal.get();
		if(jdkThreadCacheValue == null) {
			jdkThreadCacheValue = new JdkThreadCacheValue(payloadOffset, payloadLength, publicKey);
			threadLocal.set(jdkThreadCacheValue);
		}
		return jdkThreadCacheValue;
	}

	
}
