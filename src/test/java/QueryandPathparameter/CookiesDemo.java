package QueryandPathparameter;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;  //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

public class CookiesDemo {
	
	//@Test
	void testcookie() 
	{
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC","AVcja2cIq8FWsrvajD91r5N2a7eAu8kOumlTSKuyZS3fkKwxhz0WfMEpYnk")
			.log().all();
		
	}
	
	@Test
	void testmuliplecookies() 
	{
		 Response res= given()
			
		.when()
			.get("https://www.google.com/");
		 
		//String cookievalue = res.getCookie("AEC"); // to get single cookie
		 //System.out.println("My cookie value for AEC " + cookievalue);
		
		 //to get all cookies value use map
		 Map<String,String>cookiesvalue =res.getCookies();
		
		 //System.out.println("print all cookies" + cookiesvalue.keySet() );
		 
		 for(String k : cookiesvalue.keySet() ) 
		 {
			String cookiesvalue1 = res.getCookie(k);
			 System.out.println(k +"     " + cookiesvalue1);
		 }
		 
		 
	}
	
	
}
