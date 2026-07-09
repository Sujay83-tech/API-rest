package basic;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import pojo.Api;
import pojo.Courses;
import pojo.GetCourse;
import pojo.Mobile;
import pojo.WebAutomation;

public class oAuth {

    public static void main(String[] args) {

        // 1. Get Access Token
        String response = given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .asString();

        JsonPath jsonpath = new JsonPath(response);
        String accessToken = jsonpath.getString("access_token");

        // 2. Fetch Course Details using Access Token
        GetCourse response1 = given()
                .queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourse.class);

        System.out.println("LinkedIn URL: " + response1.getLinkedIn());
        System.out.println("Course URL: " + response1.getUrl());
        System.out.println("Second API Course: " + response1.getCourses().getApi().get(1).getCourseTitle());

        // 3. Find the price of a specific course dynamically
        List<Api> apiCourses = response1.getCourses().getApi();
        for (int i = 0; i < apiCourses.size(); i++) {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println("Price of SoapUI Course: $" + apiCourses.get(i).getPrice());
                break; // Stop the loop once we find what we are looking for
            }
        }

        // 4. Verify Web Automation Course Titles
        // Added the missing expected titles array
        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

        ArrayList<String> actualList = new ArrayList<>();
        List<WebAutomation> webCourses = response1.getCourses().getWebAutomation();

        for (int j = 0; j < webCourses.size(); j++) {
            actualList.add(webCourses.get(j).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertTrue(actualList.equals(expectedList), "Course titles do not match the expected list!");

        // 5. Print out all courses and prices
        System.out.println("\n--- Web Automation Courses ---");
        for (WebAutomation wa : response1.getCourses().getWebAutomation()) { // Renamed 'w' to 'wa'
            System.out.println(wa.getCourseTitle() + " - $" + wa.getPrice());
        }

        System.out.println("\n--- API Courses ---");
        for (Api apiCourse : response1.getCourses().getApi()) { // Renamed 'a' to 'apiCourse'
            System.out.println(apiCourse.getCourseTitle() + " - $" + apiCourse.getPrice());
        }

        System.out.println("\n--- Mobile Courses ---");
        for (Mobile m : response1.getCourses().getMobile()) {
            System.out.println(m.getCourseTitle() + " - $" + m.getPrice());
        }
    }
}