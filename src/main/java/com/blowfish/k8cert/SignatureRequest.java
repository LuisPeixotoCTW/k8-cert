package com.blowfish.k8cert;

public record SignatureRequest(
        String publicKeyToSign
) {}