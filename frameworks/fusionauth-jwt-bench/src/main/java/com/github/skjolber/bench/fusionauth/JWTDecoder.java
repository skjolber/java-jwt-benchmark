package com.github.skjolber.bench.fusionauth;

import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.JWTExpiredException;
import io.fusionauth.jwt.JWTUnavailableForProcessingException;
import io.fusionauth.jwt.MissingSignatureException;
import io.fusionauth.jwt.MissingVerifierException;
import io.fusionauth.jwt.NoneNotAllowedException;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.Algorithm;
import io.fusionauth.jwt.domain.Header;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.json.Mapper;

/**
 * @author Daniel DeGroff
 */
public class JWTDecoder {

    private static class Decoder {
        private final Header header;
        private final Verifier verifier;

        private Decoder(Header header, Verifier verifier) {
            this.header = header;
            this.verifier = verifier;
        }

        public void verify(String encodedJWT, String signature) {
            byte[] message = encodedJWT.substring(0, encodedJWT.length() - signature.length() - 1).getBytes(StandardCharsets.UTF_8);
            byte[] signatureBytes = base64Decode(signature);
            verifier.verify(header.algorithm, message, signatureBytes);
        }
    }

    private int clockSkew = 0;


    /**
     * Decode the JWT using one of they provided verifiers. One more verifiers may be provided, the first verifier found
     * supporting the algorithm reported by the JWT header will be utilized.
     * <p>
     * A JWT that is expired or not yet valid will not be decoded, instead a {@link JWTExpiredException} or {@link
     * JWTUnavailableForProcessingException} exception will be thrown respectively.
     *
     * @param encodedJWT The encoded JWT in string format.
     * @param verifiers  A map of verifiers.
     * @return a decoded JWT.
     */

    private Map<String, Decoder> validators = new ConcurrentHashMap<>();

    private final List<Verifier> verifiers;

    public JWTDecoder(List<Verifier> verifiers) {
        this.verifiers = verifiers;
    }

    public JWT decode(String encodedJWT) {
        int index = encodedJWT.indexOf('.');
        String headerAsString = encodedJWT.substring(0, index);

        Decoder existing = this.validators.get(headerAsString);
        if (existing == null) {
            Header header = Mapper.deserialize(base64Decode(headerAsString), Header.class);
            Verifier verifier = verifiers.stream().filter(v -> v.canVerify(header.algorithm)).findFirst().orElse(null);
            existing = new Decoder(header, verifier);
            validators.put(headerAsString, existing);
        }

        int lastIndex = encodedJWT.lastIndexOf('.');
        String signature = encodedJWT.substring(lastIndex + 1);

        existing.verify(encodedJWT, signature);

        // Signature is valid or there is no signature to validate for an un-secured JWT, verify time based JWT claims
        JWT jwt = Mapper.deserialize(base64Decode(encodedJWT.substring(index + 1, lastIndex)), JWT.class);

        ZonedDateTime now = now();
        // Verify expiration claim
        if (clockSkew != 0) {
            ZonedDateTime nowMinusSkew = now.minusSeconds(clockSkew);
            if (jwt.isExpired(nowMinusSkew)) {
                throw new JWTExpiredException();
            }

            // Verify the notBefore claim
            ZonedDateTime nowPlusSkew = now.plusSeconds(clockSkew);
            if (jwt.isUnavailableForProcessing(nowPlusSkew)) {
                throw new JWTUnavailableForProcessingException();
            }
        } else {
            if (jwt.isExpired(now)) {
                throw new JWTExpiredException();
            }
            if (jwt.isUnavailableForProcessing(now)) {
                throw new JWTUnavailableForProcessingException();
            }
        }

        return jwt;

    }

    /**
     * Specify the number of seconds allowed for clock skew used for calculating the expiration and not before instants of a JWT.
     *
     * @param clockSkew the number of seconds allowed for clock skew.
     * @return this
     */
    public JWTDecoder withClockSkew(int clockSkew) {
        this.clockSkew = clockSkew;
        return this;
    }

    /**
     * Decode the JWT using one of they provided verifiers. A JWT header value named <code>kid</code> is expected to
     * contain the key to lookup the correct verifier.
     * <p>
     * A JWT that is expired or not yet valid will not be decoded, instead a {@link JWTExpiredException} or {@link
     * JWTUnavailableForProcessingException} exception will be thrown respectively.
     *
     * @param encodedJWT The encoded JWT in string format.
     * @param verifiers  A map of verifiers.
     * @return a decoded JWT.
     */
    public JWT decode(String encodedJWT, Map<String, Verifier> verifiers) {
        return decode(encodedJWT, verifiers, h -> h.get("kid"));
    }

    /**
     * Decode the JWT using one of they provided verifiers. The key used to lookup the correct verifier is provided by the
     * <code>keyFunction</code>. The key function is provided the JWT header and is expected to return a string key to
     * look up the correct verifier.
     * <p>
     * A JWT that is expired or not yet valid will not be decoded, instead a {@link JWTExpiredException} or {@link
     * JWTUnavailableForProcessingException} exception will be thrown respectively.
     *
     * @param encodedJWT  The encoded JWT in string format.
     * @param verifiers   A map of verifiers.
     * @param keyFunction A function used to lookup the verifier key from the header.
     * @return a decoded JWT.
     */
    public JWT decode(String encodedJWT, Map<String, Verifier> verifiers, Function<Header, String> keyFunction) {
        Objects.requireNonNull(encodedJWT);
        Objects.requireNonNull(verifiers);
        Objects.requireNonNull(keyFunction);

        String[] parts = getParts(encodedJWT);

        Header header = Mapper.deserialize(base64Decode(parts[0]), Header.class);
        String key = keyFunction.apply(header);
        Verifier verifier = verifiers.get(key);

        // The 'none' algorithm is only allowed when no verifiers are provided.
        boolean allowNoneAlgorithm = verifiers.isEmpty();

        return validate(encodedJWT, parts, header, verifier, allowNoneAlgorithm);
    }

    /**
     * Decode the provided base64 encoded string.
     *
     * @param string the input string to decode, it is expected to be a valid base64 encoded string.
     * @return a decoded byte array
     */
    private static byte[] base64Decode(String string) {
        try {
            // Equal to calling : .decode(string.getBytes(StandardCharsets.ISO_8859_1))
            // If this is a properly base64 encoded string, decoding using ISO_8859_1 should be fine.
            return Base64.getUrlDecoder().decode(string);
        } catch (IllegalArgumentException e) {
            throw new InvalidJWTException("The encoded JWT is not properly Base64 encoded.", e);
        }
    }

    /**
     * Split the encoded JWT on a period (.), and return the parts.
     * <p>
     * A secured JWT will be in the format : <code>XXXXX.YYYYY.ZZZZZ</code> and an un-secured JWT (no signature) will be in the format <code>XXXXX.YYYYY</code>.
     *
     * @param encodedJWT the encoded form of the JWT
     * @return an array of parts, 2 for an un-secured JWT, and 3 parts for a secured JWT.
     */
    private String[] getParts(String encodedJWT) {
        String[] parts = encodedJWT.split("\\.");
        if (parts.length == 3 || (parts.length == 2 && encodedJWT.endsWith("."))) {
            return parts;
        }

        throw new InvalidJWTException("The encoded JWT is not properly formatted. Expected a three part dot separated string.");
    }

    /**
     * Validate the encoded JWT and return the constructed JWT object if valid.
     *
     * @param encodedJWT         the encoded JWT
     * @param parts              the parts of the encoded JWT
     * @param header             the JWT header
     * @param verifier           the selected JWT verifier
     * @param allowNoneAlgorithm true if un-secured JWTs may be decoded, i.e. 'none' algorithm is allowed
     * @return the constructed JWT object containing identity claims
     */
    private JWT validate(String encodedJWT, String[] parts, Header header, Verifier verifier, boolean allowNoneAlgorithm) {
        // When parts.length == 2, we have no signature.
        //  - Case 1: If one or more verifiers are provided, we will not decode an un-secured JWT. Throw NoneNotAllowedException
        //  - Case 2: If no verifiers are provided, we will decode an un-secured JWT, the algorithm must be 'none'.
        if (parts.length == 2) {
            if (!allowNoneAlgorithm) {
                throw new NoneNotAllowedException();
            }

            if (header.algorithm != Algorithm.none) {
                throw new MissingSignatureException("Your provided a JWT with the algorithm [" + header.algorithm.getName() + "] but it is missing a signature");
            }
        } else {
            // When parts.length == 3, we have a signature.
            // - Case 1: The algorithm in the header is 'none', we do not expect a signature.
            // - Case 2: No verifier was provided that can verify the algorithm in the header, or no verifier found by the kid in the header
            // - Case 3: The requested verifier cannot verify the signature based upon the algorithm value in the header
            if (header.algorithm == Algorithm.none) {
                throw new InvalidJWTException("You provided a JWT with a signature and an algorithm of none");
            }

            if (verifier == null) {
                throw new MissingVerifierException("No Verifier has been provided for verify a signature signed using [" + header.algorithm.getName() + "]");
            }

            // When the verifier has been selected based upon the 'kid' or other identifier in the header, we must verify it can verify the algorithm.
            // - When multiple verifiers are provided to .decode w/out a kid, we may have already called 'canVerify', this is ok.
            if (!verifier.canVerify(header.algorithm)) {
                throw new MissingVerifierException("No Verifier has been provided for verify a signature signed using [" + header.algorithm.getName() + "]");
            }

            verifySignature(verifier, header.algorithm, parts[2], encodedJWT);
        }

        // Signature is valid or there is no signature to validate for an un-secured JWT, verify time based JWT claims
        JWT jwt = Mapper.deserialize(base64Decode(parts[1]), JWT.class);
        ZonedDateTime now = now();

        // Verify expiration claim
        ZonedDateTime nowMinusSkew = now.minusSeconds(clockSkew);
        if (jwt.isExpired(nowMinusSkew)) {
            throw new JWTExpiredException();
        }

        // Verify the notBefore claim
        ZonedDateTime nowPlusSkew = now.plusSeconds(clockSkew);
        if (jwt.isUnavailableForProcessing(nowPlusSkew)) {
            throw new JWTUnavailableForProcessingException();
        }

        return jwt;
    }

    /**
     * @return the 'now' to be used to validate 'exp' and 'nbf' claims.
     */
    protected ZonedDateTime now() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    /**
     * Verify the signature of the encoded JWT. If the signature is invalid a {@link InvalidJWTSignatureException} will be thrown.
     *
     * @param verifier   the verifier
     * @param header     the JWT header
     * @param signature  the JWT signature
     * @param encodedJWT the encoded JWT
     * @throws InvalidJWTSignatureException if the JWT signature is invalid.
     */
    private void verifySignature(Verifier verifier, Algorithm algorithm, String signature, String encodedJWT) {
        // The message comprises the first two segments of the entire JWT, the signature is the last segment.
        byte[] message = encodedJWT.substring(0, encodedJWT.length() - signature.length() - 1).getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = base64Decode(signature);
        verifier.verify(algorithm, message, signatureBytes);
    }
}