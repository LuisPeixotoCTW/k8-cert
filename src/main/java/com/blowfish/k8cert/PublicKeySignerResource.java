package com.blowfish.k8cert;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import java.security.*;
import java.util.Base64;

@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)

@Path("/sign")
@ApplicationScoped
public class PublicKeySignerResource {

    //private final PublicKeySignerImpl publicKeySigner;
    private final PublicKeySignerImpl signer;
    private static final String SIG_ALG = "SHA256withRSA";

    public PublicKeySignerResource() throws NoSuchAlgorithmException, InvalidKeyException {
        this.signer = new PublicKeySignerImpl();
    }

    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sign(SignatureRequest request) {
        try {
            PublicKeySignerImpl signKey = new PublicKeySignerImpl();
            byte[] signature = signKey.sign(request.publicKeyToSign());
            var encodedSignature = Base64.getEncoder().encodeToString(signature);

            return Response.status(200).entity(new SignatureResponse(encodedSignature)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
            //throw new WebApplicationException("Erro ao assinar a mensagem", e);
        }
    }
}