package com.blowfish.k8cert;

import java.security.*;

public class PublicKeySignerImpl implements PublicKeySigner {
    // client -> envia a chave publica (poderá verificar assinatura)
    // server -> recebe chave publica de client e assina com a sua chave privada
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

    public byte[] sign(String msg) throws Exception {
        Signature rsa = Signature.getInstance(SIG_ALG);
        rsa.initSign(this.privateKey);
        rsa.update(msg.getBytes());
        return rsa.sign();
    }

    public boolean verify(String msg, byte[] signature) throws Exception {
        Signature rsa = Signature.getInstance(SIG_ALG);
        rsa.initVerify(this.publicKey);
        rsa.update(msg.getBytes());
        return rsa.verify(signature);
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}