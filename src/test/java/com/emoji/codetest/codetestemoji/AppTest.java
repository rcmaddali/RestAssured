package com.emoji.codetest.codetestemoji;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     */
    public void testApp() throws IOException
    {
    	
    	RestAssured.baseURI ="https://sand-api.emogi.com/v1";
    	RequestSpecification request = RestAssured.given();
    	System.out.println(Paths.get("."));
   		String content = new String(Files.readAllBytes(Paths.get("src/test/java/payload.json")));
    	// Add a header stating the Request body is a JSONs
    	request.header("Content-Type", "application/json");
    	request.header("Authorization", "*");
    	request.header("Emogi-id", "*");
    	// Add the Json to the body of the request
    	request.body(content);
    	// Post the request and check the response
    	Response response = request.post("/contents/search");
    	System.out.println(response.getBody().prettyPrint());
    	JsonPath responseJson= response.getBody().jsonPath();
    	String url=responseJson.getString("data.contents.assets.medium.gif.url[0]");
    	int statusCode = response.getStatusCode();
    	System.out.println(statusCode);
    	Assert.assertEquals(statusCode, 200);
    }
}
