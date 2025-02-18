package QueryandPathparameter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;  //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
public class HeadersDemo {

	//@Test
	void testHeader() 
	{
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip");
	}
	@Test
	void testHeaders() 
	{
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//String getheader = res.getHeader("Content-Type");  //to get single header value
		//System.out.println("Value of header Content-Type is " + getheader);
		
		Headers getheaders = res.getHeaders(); // to get multiple headers and values
		
		for(Header k:getheaders) 
		{
			System.out.println(k.getName()+"       " + k.getValue());
		}
	}
	
}
