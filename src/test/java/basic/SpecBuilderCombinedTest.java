package basic;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import basic.SpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderCombinedTest {

    public static void main(String[] args) {

        // ===============================================================
        // STEP 1 — BUILD THE PAYLOAD OBJECT (same for both "before" and "after")
        // ===============================================================
        // Built step-by-step via setters, not a giant constructor call.
        // This mirrors how you'd realistically build a payload in a real
        // test — often filling in only SOME fields per test scenario,
        // which a fixed-argument constructor wouldn't let you do flexibly.
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");

        // Build the List<String> separately, THEN attach it —
        // you can't inline-populate a List in the setter call itself.
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        // Build the nested Location object separately too — same reason.
        // This is the step that reflects the REAL nested JSON shape:
        // "location": { "lat": -38.38, "lng": 33.42 }
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);


        // ===============================================================
        // "BEFORE" — no spec builder, everything typed out manually.
        // (this is what serializeTest.java looks like in the original)
        // ===============================================================
        Response beforeResponse = given()
                .log().all()                              // logs the full outgoing request — useful for debugging
                .queryParam("key", "qaclick123")           // repeated manually — would be retyped in every test
                .body(p)                                   // REST Assured auto-serializes the POJO to JSON here
                .when()
                .post("https://rahulshettyacademy.com/maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200)              // repeated manually — would be retyped in every test
                .extract().response();

        System.out.println("BEFORE (no spec): " + beforeResponse.asString());


        // ===============================================================
        // "AFTER" — same request, but using SpecBuilder to avoid repetition.
        // (this is what SpecBuilderTest.java looks like in the original)
        // ===============================================================

        // Notice: given().spec(req).body(p) is stored in its own variable
        // BEFORE calling .when().post(...) — this is a valid, equally
        // correct alternative to chaining everything in one long line.
        // Splitting it like this can make debugging easier: you can
        // inspect `requestWithBody` in a debugger before firing it off.
        RequestSpecification requestWithBody = given()
                .spec(SpecBuilder.getRequestSpec())        // baseURI + content-type + key, all in ONE call
                .body(p);

        Response afterResponse = requestWithBody
                .when()
                .post("/maps/api/place/add/json")          // relative path works now because baseURI
                // is already set inside the spec
                .then()
                .spec(SpecBuilder.getResponseSpec())        // statusCode + contentType, in ONE call
                .extract().response();

        System.out.println("AFTER (with spec): " + afterResponse.asString());
    }
}