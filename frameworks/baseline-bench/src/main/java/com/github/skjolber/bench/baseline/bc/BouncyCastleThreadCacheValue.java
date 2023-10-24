package com.github.skjolber.bench.baseline.bc;

import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.RSADigestSigner;

public class BouncyCastleThreadCacheValue {

	private final SHA256Digest initial;

	private final SHA256Digest digest = new SHA256Digest(CryptoServicePurpose.VERIFYING);

	private int payloadLength;
	private final int payloadOffset;

	private final RSADigestSigner signer;

	private final byte[] buffer = new byte[256];

	public BouncyCastleThreadCacheValue(SHA256Digest initial, int headerSize, int payloadLength, RSAKeyParameters rsaKeyParameters) {
		this.initial = initial;
		this.payloadOffset = headerSize;
		this.payloadLength = payloadLength;

		this.signer = new RSADigestSigner(digest);
		this.signer.init(false, rsaKeyParameters);
	}

	public boolean validate(byte[] jwt) {
		digest.reset(initial);
		digest.update(jwt, payloadOffset, payloadLength);

		com.github.skjolber.bench.baseline.Base64.decodeFast256(jwt, payloadOffset + payloadLength + 1, buffer);
		return signer.verifySignature(buffer);
	}
	
}
