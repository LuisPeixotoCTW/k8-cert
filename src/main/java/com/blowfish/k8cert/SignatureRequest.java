package com.blowfish.k8cert;

public class SignatureRequest {
    private String publicKey;
    private String signature;

    // Getters and setters
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}