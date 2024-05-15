package com.blowfish.k8cert;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PublicKeySignerResourceTest {
    /*@Test
    void signersEndpoint() {
        given()
          .when().get("/sign")
          .then()
                // message é null, não tem nada então espera código 500 e não 200
                // Afinal para 401 uma vez que é preciso o token para poder assinar a chave
                // Alterar depois
             .statusCode(401);
    }*/
}