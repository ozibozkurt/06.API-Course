package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlHerokuappQueryParam extends HerokuAppBaseUrl {

    @Test
    public void test01(){
        /*
        1-  https://restful-booker.herokuapp.com/booking endpointine
         bir GET request gonderdigimizde donen response’un
         status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin

         */
        // 1- Requst url ve body olustur
        specHerokuApp.pathParam("Pp1","booking");
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().
                        spec(specHerokuApp).
                        when().
                        get("/{Pp1}");
        response.prettyPrint();
        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid", Matchers.hasSize(18));
    }

    @Test
    public void test02(){
        /*
        2- https://restful-booker.herokuapp.com/booking endpointine
        gerekli Query parametrelerini yazarak
        “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek
        bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin

         */
        // 1- Requst url ve body olustur
        specHerokuApp.pathParam("Pp1","booking").queryParam("firstname","Eric");
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().
                spec(specHerokuApp).
                when().
                get("/{Pp1}");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);
        Assert.assertTrue(response.asString().contains("bookingid"));


    }

    @Test
    public void test03(){
        /*
            3- https://restful-booker.herokuapp.com/booking endpointine
            gerekli Query parametrelerini yazarak “firstname” degeri “Jim”
            ve “lastname” degeri “Jackson” olan rezervasyon
            oldugunu test edecek bir GET request gonderdigimizde,
            donen response’un status code’unun 200 oldugunu
            ve “Jim Jackson” ismine sahip en az bir booking oldugunu test edin
         */
        // 1- Requst url ve body olustur
        specHerokuApp.pathParam("Pp1","booking").
                        queryParams("firstname","Jim","lastname","Wilson");
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().
                spec(specHerokuApp).
                when().
                get("/{Pp1}");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);
        Assert.assertTrue(response.asString().contains("bookingid"));


    }



}
