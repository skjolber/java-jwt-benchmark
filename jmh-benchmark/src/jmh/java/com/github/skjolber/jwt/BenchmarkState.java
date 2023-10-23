package com.github.skjolber.jwt;

import com.auth0.jwt.Auth0TokenVerifier;
import com.github.skjolber.bench.fusionauth.FusionAuthJsonWebTokenVerifier;
import com.github.skjolber.bench.jjwt.JavaJsonWebTokenVerifier;
import com.github.skjolber.bench.jjwt.KeyProvider;
import com.github.skjolber.bench.nimbus.NimbusJsonWebTokenVerifier;
import com.github.skjolber.bench.okta.OktaJsonWebTokenVerifier;
import com.github.skjolber.bench.utils.JsonWebTokenGenerator;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@State(Scope.Thread)
public class BenchmarkState {

	private String token;

	private FusionAuthJsonWebTokenVerifier fusionAuthJsonWebTokenVerifier;
	private OktaJsonWebTokenVerifier oktaJsonWebTokenVerifier;
	private JavaJsonWebTokenVerifier javaJsonWebTokenVerifier;
	private Auth0TokenVerifier auth0TokenVerifier;
	private NimbusJsonWebTokenVerifier nimbusTokenVerifier;

	@Setup(Level.Trial)
	public void init() throws Exception {
		JsonWebTokenGenerator generator = JsonWebTokenGenerator.newInstance();

		Map<String, Object> map = new HashMap<>();
		map.put("test", "value");

		String issuer = "https://test";
		String audience = "https://audience";

		token = generator.createJsonWebToken(map, issuer, audience);

		oktaJsonWebTokenVerifier = new OktaJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);

		fusionAuthJsonWebTokenVerifier = FusionAuthJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		javaJsonWebTokenVerifier = JavaJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		auth0TokenVerifier = Auth0TokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		nimbusTokenVerifier = NimbusJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
	}
	
	public Auth0TokenVerifier getAuth0TokenVerifier() {
		return auth0TokenVerifier;
	}
	
	public JavaJsonWebTokenVerifier getJavaJsonWebTokenVerifier() {
		return javaJsonWebTokenVerifier;
	}
	
	public OktaJsonWebTokenVerifier getOktaJsonWebTokenVerifier() {
		return oktaJsonWebTokenVerifier;
	}

	public FusionAuthJsonWebTokenVerifier getFusionAuthJsonWebTokenVerifier() {
		return fusionAuthJsonWebTokenVerifier;
	}

	public String getToken() {
		return token;
	}

	public NimbusJsonWebTokenVerifier getNimbusTokenVerifier() {
		return nimbusTokenVerifier;
	}
}
