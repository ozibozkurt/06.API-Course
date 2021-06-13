package test;


import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlJsonPlace extends JsonPlaceBaseUrl {

    @Test
    public void test01(){
        // 1-  https://jsonplaceholder.typicode.com/posts endpointine
        // bir GET request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve Response’ta 100 kayit oldugunu test edin

        // 1- Request url ve body olustur
        specJsonPlace.pathParam("Pp1","posts");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonderip donen sonucu kaydet
        Response response=given().spec(specJsonPlace).when().get("/{Pp1}");
        response.prettyPrint();
        // 4- Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("id", Matchers.hasSize(100));

    }

    @Test
    public void test02(){
        //2- https://jsonplaceholder.typicode.com/posts/44 endpointine
        // bir GET request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve “title” degerinin “optio dolor molestias sit” oldugunu test edin

        // 1- Requst url ve body olustur
        specJsonPlace.pathParams("Pp1","posts","Pp2",44);

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().spec(specJsonPlace).when().get("/{Pp1}/{Pp2}");
        response.prettyPrint();
        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("title",Matchers.equalTo("optio dolor molestias sit"));
    }

    @Test
    public void test03(){
        // 3- https://jsonplaceholder.typicode.com/posts/50 endpointine
        // bir DELETE request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve response body'sinin null oldugunu test edin

        // 1- Requst url ve body olustur
        specJsonPlace.pathParams("Pp1","posts","Pp2",50);
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().spec(specJsonPlace).when().delete("/{Pp1}/{Pp2}");
        response.prettyPrint();

        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("message",(Matchers.nullValue()));
    }

}
