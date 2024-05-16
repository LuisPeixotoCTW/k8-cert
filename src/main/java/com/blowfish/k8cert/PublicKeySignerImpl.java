package com.blowfish.k8cert;

import java.security.*;

public class PublicKeySignerImpl implements PublicKeySigner {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private static final String KPG_ALG = "RSA";
    private static final String SIG_ALG = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    public PublicKeySignerImpl() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KPG_ALG);
        keyGen.initialize(KEY_SIZE);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public byte[] sign(String publicKeyToSign) throws Exception {
        Signature rsa = Signature.getInstance(SIG_ALG);
        rsa.initSign(this.privateKey);
        rsa.update(publicKeyToSign.getBytes());
        byte[] publicKeySigned = rsa.sign();
        return publicKeySigned;
    }

    public boolean verify(byte[] publicKeySigned, String publicKeyToSign, PublicKey publicKey_not_real) throws Exception {
        Signature rsa = Signature.getInstance(SIG_ALG);
        rsa.initVerify(this.publicKey);
        rsa.update(publicKeyToSign.getBytes());
        boolean verified = rsa.verify(publicKeySigned);
        return verified;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}