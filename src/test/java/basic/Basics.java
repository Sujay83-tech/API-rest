package basic;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payloads;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Basics {

	public static void main(String[] args) {
	 
		//validation if Add place is working
		 
		
   	  RestAssured.baseURI="https://rahulshettyacademy.com";
		
		/* 1st
		 * String response=given().log().all().queryParam("key","qaclick123").
		 * header("Content-Type","application/json").body(payloads.AddPlace.java()).when().
		 * post("maps/api/place/add/json").then().log().all().assertThat().statusCode(
		 * 200). body("scope",equalTo(
		 * "APP")).header("server","Apache/2.4.52 (Ubuntu)").
		 */
   			
	  String response=given().log().all().queryParam("key","qaclick123").
	  header("Content-Type","application/json").body(payloads.AddPlace()).when().
	  post("maps/api/place/add/json").then().assertThat().statusCode(200).
	  body("scope",equalTo( "APP")).header("server","Apache/2.4.52 (Ubuntu)").
		
	  extract().response().asString();
	  System.out.println(response);
	  JsonPath js = new JsonPath(response);  // for parsing Json
	  String placeId=js.get("place_id");
	  System.out.println(placeId);
	  
	  //Update Place
	  String newAddress = "new add paseed";
	  
	  given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	  .body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200);
		/* .body("msg", equalTo("Address sucessfully updated")) */

	  
	  //get place
	String gerPlaceres=  given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId).
	  when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
	  .extract().response().asString();
	

	   JsonPath js1 = ReUsableMethods.rawToJson(gerPlaceres);
       String actualAddress = js1.getString("address");
       System.out.println(actualAddress);
       Assert.assertEquals(actualAddress, newAddress);
       }
	  
	
/*
    RestAssured.baseURI = "https://rahulshettyacademy.com";

        // ===================== ADD PLACE =====================
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payloads.AddPlace.java())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response); // for parsing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        // ===================== UPDATE PLACE =====================
        String newAddress = "Summer Walk, Africa";

        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\r\n"
                        + "\"place_id\":\"" + placeId + "\",\r\n"
                        + "\"address\":\"" + newAddress + "\",\r\n"
                        + "\"key\":\"qaclick123\"\r\n"
                        + "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat().log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        // ===================== GET PLACE =====================
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat().log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
        }	
 */
	
	
	}

