package basic;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.GetCourseResponse;
import pojo.Mobile;
import pojo.WebAutomation;

public class oAuth {

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

				 GetCourse response1 = given()
        .queryParam("access_token", accessToken)
        .when()
        .log().all()
        .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
        .as(GetCourse.class);

System.out.println(response1.getLinkedIn());
System.out.println(response1.getUrl());

// drill into nested data:
for (WebAutomation w : response1.getCourses().getWebAutomation()) {
    System.out.println(w.getCourseTitle() + " - $" + w.getPrice());
}
for (Api a : response1.getCourses().getApi()) {
    System.out.println(a.getCourseTitle() + " - $" + a.getPrice());
}
for (Mobile m : response1.getCourses().getMobile()) {
    System.out.println(m.getCourseTitle() + " - $" + m.getPrice());
}
	}
}