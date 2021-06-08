package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuAppBaseUrl {

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
        Response response=given().spec(specHerokuApp).when().get("/{Pp1}");
        response.prettyPrint();

        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid", Matchers.hasSize(21));
    }

    @Test
    public void test02(){
        /*
        2- https://restful-booker.herokuapp.com/booking endpointine
        asagidaki body’ye sahip bir POST request gonderdigimizde
        donen response’un status code’unun 200 oldugunu ve “firstname” degerinin “Ahmet” oldugunu test edin

            {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                   "checkin" : "2021-06-01",
                   "checkout" : "2021-06-10"
                      },
            "additionalneeds" : "wi-fi" }
         */
        // 1- Requst url ve body olustur
        specHerokuApp.pathParam("Pp1","booking");
        JSONObject requestBody =new JSONObject();
        JSONObject innerBody= new JSONObject();

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);

        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");
        requestBody.put("bookingdates",innerBody);

        requestBody.put("additionalneeds","wi-fi");
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().
                                contentType(ContentType.JSON).
                                spec(specHerokuApp).
                                when().
                                body(requestBody.toString()).
                                post("/{Pp1}");
        response.prettyPrint();

        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("booking.firstname", Matchers.equalTo("Ahmet"));

    }
    }

