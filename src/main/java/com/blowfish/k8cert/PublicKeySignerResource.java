package com.blowfish.k8cert;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;

@Path("/signers")
public class PublicKeySignerResource {

    @GET
    @RolesAllowed("admin")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String sign(@QueryParam("message") String msg) {
        try {
            PublicKeySignerImpl signKey = new PublicKeySignerImpl();
            byte[] signature = signKey.sign(msg);
            return new String(signature);

        } catch (Exception e) {
            throw new WebApplicationException("Erro ao assinar a mensagem", e);
        }
    }
}