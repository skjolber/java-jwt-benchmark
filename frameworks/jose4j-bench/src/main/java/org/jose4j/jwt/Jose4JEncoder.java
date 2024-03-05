package org.jose4j.jwt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jws.AlgorithmIdentifiers;

public class Jose4JEncoder {

    private final KeyPair keyPair;

    public Jose4JEncoder(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String generateToken(Map<String, Object> keys, String issuer, String audience) throws Exception {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(issuer);
        claims.setAudience(audience);
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture(24 * 60 * 60);

        for (Entry<String, Object> entry : keys.entrySet()) {
            claims.setClaim(entry.getKey(), entry.getValue());
        }

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey((RSAPrivateKey) keyPair.getPrivate());
        jws.setKeyIdHeaderValue("12345678");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        return jws.getCompactSerialization();
    }

}
