package com.github.skjolber.jwt;

import org.jose4j.jwt.Jose4JTokenVerifier;
import com.auth0.jwt.Auth0TokenVerifier;
import com.github.skjolber.bench.baseline.bc.BaseBouncyCastleJwtVerifier;
import com.github.skjolber.bench.baseline.jdk.BaseJdkJwtVerifier;
import com.github.skjolber.bench.fusionauth.FusionAuthJsonWebTokenVerifier;
import com.github.skjolber.bench.jjwt.JavaJsonWebTokenVerifier;
import com.github.skjolber.bench.nimbus.NimbusJsonWebTokenVerifier;
import com.github.skjolber.bench.okta.OktaJsonWebTokenVerifier;
import com.github.skjolber.bench.utils.JsonWebTokenGenerator;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;

@State(Scope.Thread)
public class BenchmarkState {

	private String token;

	private FusionAuthJsonWebTokenVerifier fusionAuthJsonWebTokenVerifier;
	private OktaJsonWebTokenVerifier oktaJsonWebTokenVerifier;
	private JavaJsonWebTokenVerifier javaJsonWebTokenVerifier;
	private Auth0TokenVerifier auth0TokenVerifier;
	private NimbusJsonWebTokenVerifier nimbusTokenVerifier;

	private BaseBouncyCastleJwtVerifier baseBouncyCastleJwtVerifier;
	private BaseJdkJwtVerifier baseJdkJwtVerifier;
	private Jose4JTokenVerifier jose4JTokenVerifier;

	@Setup(Level.Trial)
	public void init() throws Exception {
		JsonWebTokenGenerator generator = JsonWebTokenGenerator.newInstance();

		//Security.addProvider(new BouncyCastleProvider());

		Map<String, Object> map = new HashMap<>();
		map.put("test", "value");

		String issuer = "https://test";
		String audience = "https://audience";

		token = generator.createJsonWebToken(map, issuer, audience);

		// oktaJsonWebTokenVerifier = new OktaJsonWebTokenVerifier(generator.getKeyPair(), issuer, audience);

		fusionAuthJsonWebTokenVerifier = FusionAuthJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		javaJsonWebTokenVerifier = JavaJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		auth0TokenVerifier = Auth0TokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
		nimbusTokenVerifier = NimbusJsonWebTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);

		baseBouncyCastleJwtVerifier = BaseBouncyCastleJwtVerifier.newInstance(generator.getKeyPair(), issuer, audience, token);
		baseJdkJwtVerifier = BaseJdkJwtVerifier.newInstance(generator.getKeyPair(), issuer, audience, token);
		jose4JTokenVerifier = Jose4JTokenVerifier.newInstance(generator.getKeyPair(), issuer, audience);
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

	public BaseBouncyCastleJwtVerifier getBaseBouncyCastleJwtVerifier() {
		return baseBouncyCastleJwtVerifier;
	}

	public BaseJdkJwtVerifier getBaseJdkJwtVerifier() {
		return baseJdkJwtVerifier;
	}

	public Jose4JTokenVerifier getJose4JTokenVerifier() {
		return jose4JTokenVerifier;
	}
}
