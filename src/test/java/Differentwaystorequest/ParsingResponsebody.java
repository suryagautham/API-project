package Differentwaystorequest;
import static io.restassured.RestAssured.*;  //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingResponsebody {
	//@Test
	void testingjsonResponse() 
	{
		Response res=given()
			.contentType("application/json")
		.when()
			.get("http://localhost:3000/book");
	
		int statuscode = res.getStatusCode();	
		Assert.assertEquals(statuscode, 200);
		Assert.assertEquals(res.getContentType(),"application/json"); 
		
		String booktitle = res.jsonPath().get("[3].title");
		System.out.println(booktitle);
		Assert.assertEquals(booktitle, "The Lord of the Rings");
	}
	@Test
	void testingjsonResponsedata() 
	{
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/book");
		
		String response = res.asString();
		System.out.println(response);
	    JSONArray ja = new JSONArray(response);
	    
	  //userinput
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter the title name:");
		//String scan = sc.nextLine();
		//sc.close();
		boolean status= false;
		double totalprice = 0;
		for(int i=0; i< ja.length();i++) 
		{
			
			JSONObject book = ja.getJSONObject(i);
			//System.out.println("Title:" + book.getString("title") + "   "  + "Price:"  + book.getDouble("price")); //search for book title and price
			
			//search book title
			/*if(book.getString("title").equalsIgnoreCase(scan)) 
			{
				status= true;
				System.out.println("Title:" + book.getString("title"));
				System.out.println("Author:" + book.getString("author"));
				System.out.println("category:" + book.getString("category"));
				System.out.println("price:" + book.getDouble("price"));
				if(book.has("isbn")) {
					System.out.println("isbn" + book.getString("isbn"));
				}
				System.out.println("ID:"  + book.getString("id"));
				break;
			}	*/	
			
			double price1 = book.getDouble("price");
			
			totalprice = totalprice + price1;
		}
		System.out.println("The total price of all the books:" + totalprice);  // dont give print inside the loop.
		
		
	}
}
