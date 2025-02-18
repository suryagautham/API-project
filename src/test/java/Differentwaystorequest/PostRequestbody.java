package Differentwaystorequest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import Differentwaystorequest.Pojorequest;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;  //after copying these imports add 'import static' only thn it works
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequestbody {
		//1)post request using hashmap
	
		//@Test
		 void postusinghashmap() 
		{
			HashMap hm = new HashMap();
			hm.put("name", "surya");
			hm.put("age", "18");
			hm.put("grade", "12th");
			String subArr[] = {"Maths","computer","C"};
			
			hm.put("subjects", subArr);
			
			
			given()
				.contentType("application/json")
				.body(hm)
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name", equalTo("surya"))
				.body("age", equalTo("18"))
				.body("grade",equalTo("12th"))
				.body("subjects[0]", equalTo("Maths"))
				.body("subjects[1]", equalTo("computer"))
				.body("subjects[2]", equalTo("C"))
				.header("Content-Type", "application/json")
				.log().all();
			}

		 //2)post request using org.json
		 //@Test()
		 void PostUsingJsonLibrary() 
			{
				JSONObject js = new JSONObject();
				
				js.put("name", "surya");
				js.put("age", "20");
				js.put("grade", "BA");
				
				String subjectsArr[]= {"science","history","economic"};
				js.put("subjects", subjectsArr);
				
				given()
					.contentType("application/json")
					.body(js.toString()) // we should convert data to string in jsonobject only thn it converts to json and create data.
				.when()
					.post("http://localhost:3000/students")
					
				.then()
					.statusCode(201)
					.body("name", equalTo("surya"))
					.body("age", equalTo("20"))
					.body("grade",equalTo("BA"))
					.body("subjects[0]", equalTo("science"))
					.body("subjects[1]", equalTo("history"))
					.body("subjects[2]", equalTo("economic"))
					.header("Content-Type", "application/json")
					.log().all();
				}

		//3)post request using POJO
		 //@Test()
		 void testpostusingPOJO() 
			{
			 	Pojorequest data = new Pojorequest();
			 	
			 		data.setName("surya");
			 		data.setAge("20");
			 		data.setGrade("BA");
			 	
			 	String subjectsArr[] = {"science","history","economic"};
			 	data.setSubjects(subjectsArr);
			 	
			 	given()
					.contentType("application/json")
					.body(data) 
				.when()
					.post("http://localhost:3000/students")
					
				.then()
					.statusCode(201)
					.body("name", equalTo("surya"))
					.body("age", equalTo("20"))
					.body("grade",equalTo("BA"))
					.body("subjects[0]", equalTo("science"))
					.body("subjects[1]", equalTo("history"))
					.body("subjects[2]", equalTo("economic"))
					.header("Content-Type", "application/json")
					.log().all();
				}

		 
		//4)post request using external Json file
		 @Test()
		 void testpostusingExternalfile() throws FileNotFoundException 
			{
			 	File f = new File(".\\body.json");
			 	FileReader fr = new FileReader(f);
			 	JSONTokener jt = new JSONTokener(fr);
			 	JSONObject data = new JSONObject(jt);
			 	
			 	given()
					.contentType("application/json")
					.body(data.toString()) 
				.when()
					.post("http://localhost:3000/students")
					
				.then()
					.statusCode(201)
					.body("name", equalTo("John Doe"))
					.body("age", equalTo(18))
					.body("grade",equalTo("12th"))
					.body("subjects[0]", equalTo("Math"))
					.body("subjects[1]", equalTo("Physics"))
					.body("subjects[2]", equalTo("English"))
					.header("Content-Type", "application/json")
					.log().all();
				}

		 
		 
		 
		 
		//@Test 
		void Deletestudent() 
		 {
			given()
			
			.when()
				.delete("http://localhost:3000/students/fcec")
			.then()
				.statusCode(200)
				.log().all();
		 }
		
		
		
		
		
	
	
	}
