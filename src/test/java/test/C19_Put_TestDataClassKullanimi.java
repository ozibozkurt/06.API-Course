package test;

import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.Assert;
import testDataKlasoru.JsonplaceholderTestDatalari;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceBaseUrl {
    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body’e sahip bir PUT request yolladigimizda donen response’in
        status kodunun 200,
        content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

         Request Body

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }

        Response body :

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
     */

    @Test
    public void test01(){
        // 1- Requst url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);
        JsonplaceholderTestDatalari jsonplaceholderTestDatalari=new JsonplaceholderTestDatalari();
        JSONObject requestBody=jsonplaceholderTestDatalari.putRequestBody();

        // 2- Soruda varsa expected data olustur
        JSONObject expectedDataBody=jsonplaceholderTestDatalari.putRequestExpectedBody();

        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                                spec(specJsonPlace).
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                put("/{pp1}/{pp2}");


        response.prettyPrint();

        // 4- Assertion
        JsonPath respJsonPath =response.jsonPath();
        //         status kodunun 200,
        assertEquals(jsonplaceholderTestDatalari.basariliStatusCode,response.getStatusCode());
        //        content type’inin “application/json; charset=utf-8”,
        assertEquals(jsonplaceholderTestDatalari.contentType,response.contentType());
        //        Connection header degerinin “keep-alive”
        assertEquals(jsonplaceholderTestDatalari.headerConnection,response.getHeader("Connection"));
        //            "title":"Ahmet",
        assertEquals(expectedDataBody.get("title"),respJsonPath.getString("title"));
        //            "body":"Merhaba",
        assertEquals(expectedDataBody.get("body"),respJsonPath.getString("body"));
        //            "userId":10,
        assertEquals(expectedDataBody.get("userId"),respJsonPath.getInt("userId"));
        //            "id":70
        assertEquals(expectedDataBody.get("id"),respJsonPath.getInt("id"));


    }
}
