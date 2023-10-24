package com.github.skjolber.bench.baseline.bc;

import java.nio.charset.StandardCharsets;

public class BouncyCastleCache {
	
	private final byte[] header;
	private final int keyOffset;
	private final BouncyCastleCacheValue bouncyCastleCacheValue;
	
	public BouncyCastleCache(int keyOffset, String header, BouncyCastleCacheValue bouncyCastleCacheValue) {
		this.header = header.getBytes(StandardCharsets.US_ASCII);
		this.keyOffset = keyOffset;
		this.bouncyCastleCacheValue = bouncyCastleCacheValue;
	}
	
	public BouncyCastleCacheValue get(byte[] jwt) {
		if(jwt.length < header.length) {
			return null;
		}
		if(jwt[header.length - 1] != '.') {
			return null;
		}

		for(int i = keyOffset; i < header.length; i++) {
			if(header[i] != jwt[i]) {
				return null;
			}
		}
		for(int i = 0; i < keyOffset; i++) {
			if(header[i] != jwt[i]) {
				return null;
			}
		}

		return bouncyCastleCacheValue;
	}
	
}
