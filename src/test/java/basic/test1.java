package basic;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class test1 {

	public static void main(String[] args) {

		String Response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scpoe", "trust")
				.when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
				.asString();

		System.out.println(Response);

		JsonPath jsonpath = new JsonPath(Response);
		String accessToken = jsonpath.getString("access_token"); // <-- fixed key

				String Response1 = given()
		        .queryParam("access_token", accessToken)   // <-- fixed: snake_case key
		        .when()
		        .log().all()
		        .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		        .asString();

		System.out.println(Response1);
	}
}