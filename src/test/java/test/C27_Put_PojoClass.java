package test;

import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PojoJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_PojoClass extends JsonPlaceBaseUrl {
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
    public void test01() {
        // 1- Request url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);
        PojoJsonPlaceHolder requestBodyPojo= new PojoJsonPlaceHolder("Ahmet","Merhaba",10,70);
        System.out.println(requestBodyPojo);

        // 2- soruda varsa expected data olustur
        PojoJsonPlaceHolder expectedDataPojo= new PojoJsonPlaceHolder("Ahmet","Merhaba",10,70);

        // 3- Response olustur, request gonderip donen response'i kaydet
        Response response=given().
                spec(specJsonPlace).
                contentType(ContentType.JSON).
                body(requestBodyPojo).when().
                put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assert

        // expected data pojo ==> response

        PojoJsonPlaceHolder responsePojo=response.as(PojoJsonPlaceHolder.class);
        // expected data pojo ==> responsePojo

        assertEquals(expectedDataPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expectedDataPojo.getBody(),responsePojo.getBody());
        assertEquals(expectedDataPojo.getUserId(),responsePojo.getUserId());
        assertEquals(expectedDataPojo.getId(),responsePojo.getId());







    }

}
