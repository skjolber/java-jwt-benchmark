package com.github.skjolber.bench.baseline;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base64DecodeTest {

    @Test
    public void decodetest() {

        Base64.Decoder decoder = Base64.getUrlDecoder();

        byte[] encoded = "IAZl4KQKDdxu6O5KF0OozrGXeOCQUR-dtQNC2xR1SYpklDlyovA1qCv_ZVyuTD6eTqfCTTXLSj4UiQ8ea0W6NmeOSlgO_7sNgBMxk-AOFNs49yf0V7etyPbbn3K9TsoQRYMV8AQ6G1eVkluDzt4JTjgA4zxFU00s0LXiUU-DlJFNKV4maw-bygBwYl8OdJerawbyZlRGet-pVQmmuO28aJWTRb-6-DX0i1sGciNYC5vQz0UGpu6dkbyjZQVibCUppZ89Ui5OopkzS2Qxh2eard70Cb14Wv5v78Nkr51VO4MONnWzQSVuWZjUuln7UN23c3qne6SjR1T7VazZ1PHyew".getBytes(StandardCharsets.UTF_8);

        byte[] bytes = com.github.skjolber.bench.baseline.Base64.decodeFast(encoded, 0, 256);

        System.out.println("1: " + new String(bytes, StandardCharsets.UTF_8));

        byte[] decode = decoder.decode(encoded);

        System.out.println("2: " + new String(decode, StandardCharsets.UTF_8));

        assertEquals(bytes.length, decode.length);
        assertArrayEquals(bytes, decode);
    }

    @Test
    public void decodetest2() {

        Base64.Decoder decoder = Base64.getUrlDecoder();

        byte[] encoded = "IAZl4KQKDdxu6O5KF0OozrGXeOCQUR-dtQNC2xR1SYpklDlyovA1qCv_ZVyuTD6eTqfCTTXLSj4UiQ8ea0W6NmeOSlgO_7sNgBMxk-AOFNs49yf0V7etyPbbn3K9TsoQRYMV8AQ6G1eVkluDzt4JTjgA4zxFU00s0LXiUU-DlJFNKV4maw-bygBwYl8OdJerawbyZlRGet-pVQmmuO28aJWTRb-6-DX0i1sGciNYC5vQz0UGpu6dkbyjZQVibCUppZ89Ui5OopkzS2Qxh2eard70Cb14Wv5v78Nkr51VO4MONnWzQSVuWZjUuln7UN23c3qne6SjR1T7VazZ1PHyew".getBytes(StandardCharsets.UTF_8);

        byte[] encodedShifted = new byte[encoded.length + 100];
        System.arraycopy(encoded, 0, encodedShifted, 100, encoded.length);

        byte[] bytes = com.github.skjolber.bench.baseline.Base64.decodeFast(encodedShifted, 100, 256);

        System.out.println("1: " + new String(bytes, StandardCharsets.UTF_8));

        byte[] decode = decoder.decode(encoded);

        System.out.println("2: " + new String(decode, StandardCharsets.UTF_8));

        assertEquals(bytes.length, decode.length);
        assertArrayEquals(bytes, decode);
    }

}
