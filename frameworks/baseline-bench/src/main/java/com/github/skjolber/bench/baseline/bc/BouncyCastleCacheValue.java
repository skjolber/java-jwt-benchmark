package com.github.skjolber.bench.baseline.bc;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import java.math.BigInteger;

public class BouncyCastleCacheValue {

	private final ThreadLocal<BouncyCastleThreadCacheValue> threadLocal = new ThreadLocal<>();
	
	private final SHA256Digest digest;

	private final int payloadLength;

	private final int payloadOffset;

	private final RSAKeyParameters rsaKeyParameters;

	public BouncyCastleCacheValue(SHA256Digest digest, int payloadOffset, int payloadLength, BigInteger modulus, BigInteger publicExponent) {
		this.digest = digest;
		this.payloadOffset = payloadOffset;
		this.payloadLength = payloadLength;
		this.rsaKeyParameters = new RSAKeyParameters(false, modulus, publicExponent);
	}

	public BouncyCastleThreadCacheValue getThreadCacheValue() {
		BouncyCastleThreadCacheValue bouncyCastleThreadCacheValue = threadLocal.get();
		if(bouncyCastleThreadCacheValue == null) {
			bouncyCastleThreadCacheValue = new BouncyCastleThreadCacheValue(digest, payloadOffset, payloadLength, rsaKeyParameters);
			threadLocal.set(bouncyCastleThreadCacheValue);
		}
		return bouncyCastleThreadCacheValue;
	}

	
}
