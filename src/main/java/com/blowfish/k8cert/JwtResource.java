package com.blowfish.k8cert;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.ApplicationScoped;

@Path("/access_token")
@ApplicationScoped
public class JwtResource {

    @Inject
    JwtService service;

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response getJWT() {
        String jwt = service.generateJwt();
        return Response.ok(jwt).build();
    }
}