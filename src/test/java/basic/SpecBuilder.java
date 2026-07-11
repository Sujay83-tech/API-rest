package basic;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    // Centralizing the request spec means: baseURI, content-type, and the
    // API key query param are defined ONCE here, instead of being retyped
    // in every single test method (the "before" version, serializeTest.java,
    // repeats queryParam("key", "qaclick123") — imagine repeating that
    // across 20 test methods and then the key changes).
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();
    }

    // Same DRY principle for the response side: every test that expects
    // "200 + JSON content-type" reuses this one spec instead of chaining
    // .statusCode(200).contentType(JSON) manually every time.
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}