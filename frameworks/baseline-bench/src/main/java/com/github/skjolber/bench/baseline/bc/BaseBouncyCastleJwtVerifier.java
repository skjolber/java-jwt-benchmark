package com.github.skjolber.bench.baseline.bc;

import org.bouncycastle.crypto.digests.SHA256Digest;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

public class BaseBouncyCastleJwtVerifier {

	public static BaseBouncyCastleJwtVerifier newInstance(KeyPair keyPair, String issuer, String audience, String jwt) {

		SHA256Digest digest = new SHA256Digest();

		int headerSize = jwt.indexOf('.') + 1;

		String headerString = jwt.substring(0, headerSize);

		byte[] headerBytes = headerString.getBytes(StandardCharsets.US_ASCII);

		digest.update(headerBytes, 0, headerSize);

		int lastHeaderSize = jwt.lastIndexOf('.');

		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

		BouncyCastleCacheValue bouncyCastleCacheValue = new BouncyCastleCacheValue(digest, headerSize, lastHeaderSize - headerSize, rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());

		BouncyCastleCache bouncyCastleCache = new BouncyCastleCache(headerSize / 2, headerString, bouncyCastleCacheValue);

		return new BaseBouncyCastleJwtVerifier(issuer, audience, bouncyCastleCache);
	}

	private final String issuer;
	private final String audience;

	private final BouncyCastleCache bouncyCastleCache;

	public BaseBouncyCastleJwtVerifier(String issuer, String audience, BouncyCastleCache bouncyCastleCache) {
		this.issuer = issuer;
		this.audience = audience;
		this.bouncyCastleCache = bouncyCastleCache;
	}

	public boolean verifyJsonWebToken(String token) throws Exception {
		byte[] tokenBytes = token.getBytes(StandardCharsets.US_ASCII);
		BouncyCastleCacheValue bouncyCastleCacheValue = bouncyCastleCache.get(tokenBytes);

		if(bouncyCastleCacheValue == null) {
			return false;
		}

		BouncyCastleThreadCacheValue digest = bouncyCastleCacheValue.getThreadCacheValue();

		return digest.validate(tokenBytes);
	}


}
