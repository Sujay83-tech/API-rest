package files;

public class payloads {
	
	public static String AddPlace()
	//Given - all input details
			//when - Submit the API - resourse, HTTP method
			//then - validate the response
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 90,\r\n"
				+ "  \"name\": \"Blli ce\",\r\n"
				+ "  \"phone_number\": \"(+91) 999 893 3937\",\r\n"
				+ "  \"address\": \"29, side laydffdt, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe pork\",\r\n"
				+ "    \"ship\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"Jp-IN\"\r\n"
				+ "}";
	}

	/*
	 * public static String UpdatePlace()
	 * 
	 * { return "{\r\n" + "\"place_id\":\""+placeId+\",\r\n" +
	 * "\"address\":\"70 winter walk, USA\",\r\n" + "\"key\":\"qaclick123\"\r\n" +
	 * "}\r\n" + "}";
	 * 
	 * }
	 */
	
	public static String CoursePrice() {
	    return "{"
	        + "\"dashboard\": {"
	        + "    \"purchaseAmount\": 910,"
	        + "    \"website\": \"rahulshettyacademy.com\""
	        + "},"
	        + "\"courses\": ["
	        + "    { \"title\": \"Selenium Python\", \"price\": 50, \"copies\": 6 },"
	        + "    { \"title\": \"Cypress\",          \"price\": 40, \"copies\": 4 },"
	        + "    { \"title\": \"RPA\",              \"price\": 45, \"copies\": 10 }"
	        + "]"
	        + "}";
	}
	
	public static String AddBook(String isbn, String aisle) {
	    String body = "{\r\n"
	        + "\"name\":\"Learn Appium Automation with Java\",\r\n"
	        + "\"isbn\":\""+isbn+"\",\r\n"
	        + "\"aisle\":\""+aisle+"\",\r\n"
	        + "\"author\":\"John foe\"\r\n"
	        + "}";
	    
	    return body;  // ✅ returns the string, not the method itself
	}
	}
