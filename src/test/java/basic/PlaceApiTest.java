package basic;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.DeletePlacePayload;
import pojo.Location;

public class PlaceApiTest {

    public static void main(String[] args) {

        // -------------------------------------------------------------
        // ADD PLACE — built using the REAL POJO structure (AddPlace + Location),
        // not the flat-string version from earlier.
        // -------------------------------------------------------------
        AddPlace newPlace = new AddPlace();
        newPlace.setAccuracy(50);
        newPlace.setName("Google Shoes!");
        newPlace.setPhone_number("(02) 9374 4000");
        newPlace.setAddress("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
        newPlace.setWebsite("https://www.google.com.au/");
        newPlace.setLanguage("en-AU");

        List<String> types = new ArrayList<>();
        types.add("shoe_store");
        newPlace.setTypes(types);

        // location is a NESTED object, not a flat string —
        // this is the correction from the earlier version
        Location location = new Location();
        location.setLat(-33.8670522);
        location.setLng(151.1957362);
        newPlace.setLocation(location);

        Response addResponse = given()
                .spec(SpecBuilder.getRequestSpec())
                .body(newPlace)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();

        String placeId = new JsonPath(addResponse.asString()).getString("place_id");
        System.out.println("Place added, ID: " + placeId);

        // -------------------------------------------------------------
        // GET PLACE
        // -------------------------------------------------------------
        Response getResponse = given()
                .spec(SpecBuilder.getRequestSpec())
                .when()
                .get("/maps/api/place/get/json")
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();

        System.out.println("Get response: " + getResponse.asString());

        // -------------------------------------------------------------
        // DELETE PLACE
        // -------------------------------------------------------------
        DeletePlacePayload deletePlace = new DeletePlacePayload(placeId);

        Response deleteResponse = given()
                .spec(SpecBuilder.getRequestSpec())
                .body(deletePlace)
                .when()
                .post("/maps/api/place/delete/json")
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();

        System.out.println("Delete response: " + deleteResponse.asString());
    }
}