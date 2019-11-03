package com.github.skjolber.bench.fusionauth;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.skjolber.bench.fusionauth.FusionAuthJsonWebTokenVerifier;
import com.github.skjolber.bench.utils.JsonWebTokenGenerator;

public class FustionAuthJsonWebTokenVerifierTest {

    @Test
    public void testVerifier() throws Exception {
        JsonWebTokenGenerator generator = new JsonWebTokenGenerator();
        
        Map<String, Object> map = new HashMap<>();
        map.put("test", "value"); 
        
        String issuer = "https://test";
        String audience = "https://audience";
        
        String token = generator.createJsonWebToken(map, issuer, audience);

        FusionAuthJsonWebTokenVerifier verifier = new FusionAuthJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);
        
        verifier.verifyJsonWebToken(token);
    }
}
