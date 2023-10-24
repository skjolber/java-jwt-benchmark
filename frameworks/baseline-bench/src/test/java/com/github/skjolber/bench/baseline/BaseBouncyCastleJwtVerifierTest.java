package com.github.skjolber.bench.baseline;
import com.github.skjolber.bench.baseline.bc.BaseBouncyCastleJwtVerifier;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseBouncyCastleJwtVerifierTest {

    @Test
    public void test() throws Exception {
        JsonWebTokenGenerator generator = JsonWebTokenGenerator.newInstance();

        Map<String, Object> map = new HashMap<>();
        map.put("test", "value");

        String issuer = "https://test";
        String audience = "https://audience";

        String token = generator.createJsonWebToken(map, issuer, audience);

        System.out.println("Token " + token);

        BaseBouncyCastleJwtVerifier verifier = BaseBouncyCastleJwtVerifier.newInstance(generator.getKeyPair(), issuer, audience, token);

        assertTrue(verifier.verifyJsonWebToken(token));
    }

}
