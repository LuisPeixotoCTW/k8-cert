package com.blowfish.k8cert;

public record VerificationRequest(
        String publicKeyToSign,
        String publicKeySigned
) {}