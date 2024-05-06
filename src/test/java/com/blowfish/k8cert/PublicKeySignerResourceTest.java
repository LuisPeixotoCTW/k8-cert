package com.blowfish.k8cert;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PublicKeySignerResourceTest {
    @Test
    void signersEndpoint() {
        given()
          .when().get("/signers")
          .then()
                //message é null, não tem nada então espera código 500 e não 200
                // Alterar depois
             .statusCode(500);
    }
}