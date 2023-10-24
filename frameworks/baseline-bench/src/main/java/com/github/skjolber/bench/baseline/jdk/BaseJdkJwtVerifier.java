package com.github.skjolber.bench.baseline.jdk;

import org.bouncycastle.crypto.digests.SHA256Digest;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

public class BaseJdkJwtVerifier {

	public static BaseJdkJwtVerifier newInstance(KeyPair keyPair, String issuer, String audience, String jwt) {
		int headerSize = jwt.indexOf('.') + 1;

		String headerString = jwt.substring(0, headerSize);

		int lastHeaderSize = jwt.lastIndexOf('.');

		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

		JdkCacheValue jdkCacheValue = new JdkCacheValue(headerSize, lastHeaderSize - headerSize, rsaPublicKey);

		JdkFastCache jdkFastCache = new JdkFastCache(headerSize / 2, headerString, jdkCacheValue);

		return new BaseJdkJwtVerifier(issuer, audience, jdkFastCache);
	}

	private final String issuer;
	private final String audience;

	private final JdkFastCache jdkFastCache;

	public BaseJdkJwtVerifier(String issuer, String audience, JdkFastCache jdkFastCache) {
		this.issuer = issuer;
		this.audience = audience;
		this.jdkFastCache = jdkFastCache;
	}

	public boolean verifyJsonWebToken(String token) throws Exception {
		byte[] tokenBytes = token.getBytes(StandardCharsets.US_ASCII);
		JdkCacheValue jdkCacheValue = jdkFastCache.get(tokenBytes);

		if(jdkCacheValue == null) {
			return false;
		}

		JdkThreadCacheValue digest = jdkCacheValue.getThreadCacheValue();

		return digest.validate(tokenBytes);
	}


}
