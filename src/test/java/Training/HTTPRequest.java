package Training;
import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

// Below imports should be manually copied from doc
import static io.restassured.RestAssured.*;   //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class HTTPRequest {
	
	int id;
	@Test(priority=1)
	void Getuser() 
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	@Test(priority=2)
	void Createuser() 
	{
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name","john");
		hm.put("job", "IT");
		
		
		
		id=given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
		
	}
	
	@Test(priority=3, dependsOnMethods= {"Createuser"})
	void updateuser() 
	{
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name","Hammer");
		hm.put("job", "QA Manager");
		
		
		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	
	}
	
	@Test(priority=4)
	void Deleteuser() 
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(204)
			.log().all();
		
	}
	

	
	}
	

