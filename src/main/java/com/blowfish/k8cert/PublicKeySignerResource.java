package com.blowfish.k8cert;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import java.security.*;
import java.util.Base64;

import static io.vertx.ext.auth.impl.jose.JWS.verifySignature;

@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)

@Path("/signers")
@ApplicationScoped
public class PublicKeySignerResource {

    //private final PublicKeySignerImpl publicKeySigner;
    private final PublicKeySignerImpl signer;
    private static final String SIG_ALG = "SHA256withRSA";

    public PublicKeySignerResource() throws NoSuchAlgorithmException {
        this.signer = new PublicKeySignerImpl();
    }

    @GET
    @RolesAllowed("admin")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String sign(@QueryParam("public_key") String msg) {
        try {
            PublicKeySignerImpl signKey = new PublicKeySignerImpl();
            byte[] signature = signKey.sign(msg);
            return Base64.getEncoder().encodeToString(signature); // Convert to Base64 string

        } catch (Exception e) {
            throw new WebApplicationException("Erro ao assinar a mensagem", e);
        }
    }

    @POST
    @Path("/verify")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String verify(SignatureRequest request) {
        try {
            byte[] signature = Base64.getDecoder().decode(request.getSignature());
            boolean isValid = signer.verify(request.getMessage(), signature);
            return isValid ? "Valid" : "Invalid";
        } catch (Exception e) {
            throw new WebApplicationException("Error verifying the signature", e);
        }
    }
}