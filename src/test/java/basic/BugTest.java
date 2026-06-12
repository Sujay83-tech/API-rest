package basic;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;
public class BugTest {
	public static void main(String[] args)
	{

			RestAssured.baseURI="https://sujaybiswas3102.atlassian.net/";
			String createIssueResponse 	= given().header("Content-Type","application/json")
					.header("Authorization","Basic c3VqYXliaXN3YXMzMTAyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBIdll3Z1RUcnBYV2EtOGRQZVloTzBlWEdsRFQ5bXl1TzhLZERRVll6ckNqWllacS1wUS1Wb2NTMFVSbDVoeHc0bUdlT3drWkJ5UVdhYXlQNnNOVWJJYmMySExHVXN2SDN3OFQ5WEJIZm85TVdwZ2EyQVhwUkI4S0lkNXZvVllId3NOR3hJWC1Pc0V5UnEzVEwtZ0RuTTdOaDI0ZTl0aFZyZC1CM3ZlaVRCaEE9NzlDMjczNTk=")		.
					body("{\n"
					      + "    \"fields\": {\n"
						  + "       \"project\":\n"
					      + "       {\n"
						  + "          \"key\": \"AT2002\"\n"
					      + "       },\n"
						  + "       \"summary\": \"Website items are not working- automation\",\n"
					      + "       \"issuetype\": {\n"
						  + "          \"name\": \"Bug\"\n"
					      + "       }\n"
						  + "   }\n"
					      + "}")
					.log().all()
					.post("rest/api/3/issue")
					.then().log().all()
					.assertThat().statusCode(201)
					.contentType("application/json")
					.extract().response().asString();

			JsonPath js = new JsonPath(createIssueResponse);
			String issueKey = js.getString("key");
			System.out.println(issueKey);


			given().pathParam("key", issueKey)
			.header("X-Atlassian-Token","no-check")
			.header("Authorization","Basic c3VqYXliaXN3YXMzMTAyQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBIdll3Z1RUcnBYV2EtOGRQZVloTzBlWEdsRFQ5bXl1TzhLZERRVll6ckNqWllacS1wUS1Wb2NTMFVSbDVoeHc0bUdlT3drWkJ5UVdhYXlQNnNOVWJJYmMySExHVXN2SDN3OFQ5WEJIZm85TVdwZ2EyQVhwUkI4S0lkNXZvVllId3NOR3hJWC1Pc0V5UnEzVEwtZ0RuTTdOaDI0ZTl0aFZyZC1CM3ZlaVRCaEE9NzlDMjczNTk=")
			.multiPart("file", new File("D:\\sign.jpg")).log().all().log().all()
			.post("rest/api/3/issue/{key}/attachments")
			.then().log().all().assertThat().statusCode(200);


	}
}