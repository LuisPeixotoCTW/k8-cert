package com.blowfish.k8cert;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/signers")
public class PublicKeySignerResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public String sign(@QueryParam("message") String msg) {
        try {
            KeySigner signKey = new KeySigner();
            byte[] signature = signKey.sign(msg);
            return new String(signature);

        } catch (Exception e) {
            throw new WebApplicationException("Erro ao assinar a mensagem", e);
        }
    }
}