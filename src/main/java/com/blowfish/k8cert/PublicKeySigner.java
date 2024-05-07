package com.blowfish.k8cert;

public interface PublicKeySigner {
    byte[] sign(String msg) throws Exception;
}