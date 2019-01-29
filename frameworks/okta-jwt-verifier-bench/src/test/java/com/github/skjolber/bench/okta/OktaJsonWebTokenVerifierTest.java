package com.github.skjolber.bench.okta;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.skjolber.bench.utils.JsonWebTokenGenerator;
import com.okta.jwt.Jwt;

public class OktaJsonWebTokenVerifierTest {

    @Test
    public void testVerifier() throws Exception {
        JsonWebTokenGenerator generator = new JsonWebTokenGenerator();
        
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        
        String issuer = "https://test";
        String audience = "https://audience";
        
        String token = generator.createJsonWebToken(map, issuer, audience);

        OktaJsonWebTokenVerifier oktaJsonWebTokenVerifier = new OktaJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);
        
        oktaJsonWebTokenVerifier.verifyJsonWebToken(token);
    }
}
