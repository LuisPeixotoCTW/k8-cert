package com.blowfish.k8cert;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import java.security.*;
import java.util.Base64;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;

@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)

@Path("/sign")
@ApplicationScoped
public class PublicKeySignerResource {
    private final PublicKeySignerImpl impl;

    public PublicKeySignerResource() throws NoSuchAlgorithmException {
        this.impl = new PublicKeySignerImpl();
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sign(SignatureRequest request) {
        try {
            byte[] publicKeySigned = impl.sign(request.publicKeyToSign());
            var encodedSignature = Base64.getEncoder().encodeToString(publicKeySigned);

            return Response.status(200).entity(new SignatureResponse(encodedSignature)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/verify")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifySignature(VerificationRequest request) {
        try {
            PublicKey publicKey  = impl.getPublicKey();
            byte[] publicKeySignedBytes = Base64.getDecoder().decode(request.publicKeySigned());
            boolean isValid = impl.verify(publicKeySignedBytes, request.publicKeyToSign(), publicKey);

            if (isValid) {
                return Response.ok().entity("Valid").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}