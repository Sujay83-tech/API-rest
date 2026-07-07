package basic;

import io.restassured.path.json.JsonPath;
import files.payloads;

public class JsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(payloads.CoursePrice());

        // Get total number of courses
        int count = js.getInt("courses.size()");
        System.out.println("Total Courses: " + count);

        // Print Purchase Amount
        int amount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount: " + amount);

        // Print first and second course titles
        String title = js.getString("courses[0].title");
        System.out.println("Course 1: " + title);

        String title1 = js.getString("courses[1].title");
        System.out.println("Course 2: " + title1);

        // Print all course titles and prices using loop
        for (int i = 0; i < count; i++) {
            String courseTitles = js.getString("courses[" + i + "].title");
     //       int coursePrices   = js.getInt("courses[" + i + "].price");  // ✅ fixed: "price" not "prices"

            System.out.println(js.get("courses["+i+"].price").toString());
            
            System.out.println("Title : " + courseTitles);
           // System.out.println("Price : " + coursePrices);
            
            //Number of Copies Sold
            System.out.println("Number of copies sold by RPA Courses");
            for (int i1 = 0; i1 < count; i1++) {
                String courseTitles1 = js.getString("courses[" + i1 + "].title");
                if(courseTitles1.equalsIgnoreCase("RPA"))
                {
                	 System.out.println(js.get("courses["+i1+"].copies").toString());
                	 break;
                }
    }
        }
}
    
}