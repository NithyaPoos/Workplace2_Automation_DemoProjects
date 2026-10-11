package Demo1;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

	/*given()
	 * content type,set cookies,add auth,add parm,set headers info,etc....
	 *when()
	 * get,post,put,delete
	 *then()
	 * validate status code,extract response,extract headers cookies and response body
	 */
public class HttpRequests {
	int id;
     @Test(priority=1)
	void getUsersList() {
		given()
		.when()
		     .get("https://reqres.in/api/users?page=2")
		.then()
		     .statusCode(200)
		     .body("page",equalTo(2))
		     .log().all();
	}
     @Test(priority=2)
     void postUsercreation() {
    	 
    	 HashMap data1=new HashMap();
    	 data1.put("name", "demo1");
    	 data1.put("job", "trainer");
    	 
    	id=given()
    	      .contentType("application/json")
    	      .body(data1)
    	      
    	 .when()
    	     .post("https://reqres.in/api/users")
    	     .jsonPath().getInt("id");
//    	     
//    	 .then()
//    	     .statusCode(201)
//    	     .log().all();
     }
     @Test(priority=3,dependsOnMethods={"postUsercreation"})
     void putUserUpdation_byId() {
    	 HashMap data1=new HashMap();
    	 data1.put("name", "demo1");
    	 data1.put("job", "programmer");
    	 
    	given()
    	      .contentType("application/json")
    	      .body(data1)
    	      
    	.when()
    	     .put("https://reqres.in/api/users/"+id)
    	     
    	.then()
	     .statusCode(200)
	     .log().all();
    	     
     }
     @Test(priority=4)
     void deleteUser() {
    	 given()
    	 .when()
    	   .delete("https://reqres.in/api/users/"+id)
    	 .then()
    	    .statusCode(204)
    	    .log().all();
     }
}
