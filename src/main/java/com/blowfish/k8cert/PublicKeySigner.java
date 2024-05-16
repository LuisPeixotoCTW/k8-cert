package com.blowfish.k8cert;

import java.security.PublicKey;

public interface PublicKeySigner {
    byte[] sign(String publicKeyToSign) throws Exception;
    boolean verify(byte[] publicKeySigned, String publicKeyToSign, PublicKey publicKey_not_real) throws Exception;
}