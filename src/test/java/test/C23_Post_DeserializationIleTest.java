package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;
import testDataKlasoru.HerokuappTestDatalari;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_Post_DeserializationIleTest extends HerokuAppBaseUrl {
/*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }

    	            	Response Body
    	            {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                        ,
                        "additionalneeds":"wi-fi"
                    }
     */

    @Test
    public void test(){
       // 1- Request url ve body olustur
        specHerokuApp.pathParam("pp1","booking");
        HerokuappTestDatalari herokuappTestDatalari=new HerokuappTestDatalari();
        Map<String,Object> requestBodyMap= herokuappTestDatalari.postRequestBodyOlusturMap();


        // 2- soruda varsa expected Data olustur
        Map<String,Object> expectedDataMap=herokuappTestDatalari.postExpectedDataOlusturMap();

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                            spec(specHerokuApp).
                            contentType(ContentType.JSON).
                            body(requestBodyMap).
                            when().
                            post("/{pp1}");

        response.prettyPrint();

        // 4- Assertions

        Map<String,Object> responseMap=response.as(HashMap.class);
        System.out.println("responseMap : " + responseMap);
        System.out.println("expectedMap : " + expectedDataMap);
        assertEquals(((Map)expectedDataMap.get("booking")).get("firstname"), ((Map)responseMap.get("booking")).get("firstname"));




    }

}
