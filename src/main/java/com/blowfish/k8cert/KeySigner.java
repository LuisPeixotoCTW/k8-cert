package com.blowfish.k8cert;

import java.security.*;

public class KeySigner {
    // client -> envia a chave publica (poderá verificar assinatura)
    // server -> recebe chave publica de client e assina com a sua chave privada
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    public String KPG_alg = "RSA";
    public String Sig_rsa = "SHA256withRSA";
    public int Keysize = 2048;


    public KeySigner() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public byte[] sign(String msg) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(this.privateKey);
        rsa.update(msg.getBytes());
        return rsa.sign();
    }
}