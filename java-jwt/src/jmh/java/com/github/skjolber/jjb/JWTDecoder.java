package com.github.skjolber.jjb;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTDecoder {

    private final JWTVerifier jwtVerifier;

    public JWTDecoder(KeyProvider keyProvider) {
        this.jwtVerifier = createJwtVerifier(keyProvider);
    }

    public DecodedJWT verifyAndDecodeToken(String token) {
        return jwtVerifier.verify(token);
    }

    private JWTVerifier createJwtVerifier(KeyProvider keyProvider) {
        return JWT
                .require(Algorithm.RSA256(keyProvider))
                .build();
    }
}
