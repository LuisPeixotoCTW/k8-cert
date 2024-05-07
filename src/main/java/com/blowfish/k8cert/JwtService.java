package com.blowfish.k8cert;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class JwtService {
    private static final String PRJ_PATH = "src";
    private static final String USER = "admin";
    private static final int TIME_MS = 3600;

    public String generateJwt() {
        long duration = System.currentTimeMillis() + TIME_MS;
        Set<String> roles = new HashSet<>(
                List.of(USER)
        );

        return Jwt.issuer(PRJ_PATH)
                .subject(PRJ_PATH)
                .groups(roles)
                .expiresAt(duration)
                .sign();
    }
}