package test;

import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataKlasoru.JsonplaceholderTestDatalari;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceBaseUrl {
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
    public void Test01(){
        // 1- Request url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);
        JsonplaceholderTestDatalari jsonplaceholderTestDatalari=new JsonplaceholderTestDatalari();

        Map<String,Object> requestBodyMap= jsonplaceholderTestDatalari.requestBodyMapOlustur();

        // 2- soruda varsa expected data olustur

        Map<String,Object> expectedtBodyMap= jsonplaceholderTestDatalari.expectedBodyMapOlustur();


        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                contentType(ContentType.JSON).
                spec(specJsonPlace).
                when().
                body(requestBodyMap).
                put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assert

        Map<String,Object> responseMap = response.as(HashMap.class);
        System.out.println(responseMap);

        assertEquals(responseMap.get("title"),expectedtBodyMap.get("title"));
        assertEquals(responseMap.get("body"),expectedtBodyMap.get("body"));
        assertEquals(responseMap.get("userId"),expectedtBodyMap.get("userId"));
        assertEquals(responseMap.get("id"),expectedtBodyMap.get("id"));

    }
}
