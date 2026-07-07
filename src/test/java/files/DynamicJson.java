package files; 

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given; 


public class DynamicJson {

    @Test(dataProvider="booksData")
    public void addBook(String isbn, String aisle) 
    
    {
        RestAssured.baseURI = "http://216.10.245.166";

        Response res = given()
        		.header("Content-Type", "application/json")
        		.body(payloads.AddBook(isbn,aisle))
        		.when()
                .post("Library/Addbook.php")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().
                response();
     
        JsonPath js = ReUsableMethods.rawToJson(res.asString()); // ✅ convert to String first
        String id = js.get("ID");
        System.out.println(id);
         
      
    }
    
    @DataProvider(name = "booksData")
    
    public Object[][] getData()
    {
      return new Object[][] {{"milio","674"}, {"fgdfgf","43534"}, {"fdasd","343"}};	
    }
    
    
    
    
}             