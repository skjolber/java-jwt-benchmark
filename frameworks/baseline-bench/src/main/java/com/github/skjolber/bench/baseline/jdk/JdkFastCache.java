package com.github.skjolber.bench.baseline.jdk;

import java.nio.charset.StandardCharsets;

public class JdkFastCache {
	
	private final byte[] header;
	private final int keyOffset;
	private final JdkCacheValue jdkCacheValue;
	
	public JdkFastCache(int keyOffset, String header, JdkCacheValue jdkCacheValue) {
		this.header = header.getBytes(StandardCharsets.US_ASCII);
		this.keyOffset = keyOffset;
		this.jdkCacheValue = jdkCacheValue;
	}
	
	public JdkCacheValue get(byte[] jwt) {
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

		return jdkCacheValue;
	}
	
}
