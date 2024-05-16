package com.blowfish.k8cert;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PublicKeySignerResourceTest {
    @Test
    void signersEndpoint() {
        given()
          .when().get("/sign")
          .then()
             .statusCode(405);
    }
}