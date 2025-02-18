package QueryandPathparameter;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;  //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class QandPParameterRequest 
{
	@Test
	void testqueryandpathparameter() 
	{
		//https://reqres.in/api/users?page=2&=5
		given()
			.pathParam("mypath", "users")  //pathparameter
			.queryParam("page", 2)  //queryparameter
			.queryParam("id", 5)
		
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
}
