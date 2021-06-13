package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.HerokuappTestDatalari;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerokuAppBaseUrl {

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
    public void postTest() {
        // 1- request icin url ve body hazirla
        specHerokuApp.pathParam("pp1","booking");

        HerokuappTestDatalari herokuappTestDatalari=new HerokuappTestDatalari();
        JSONObject requestBody= herokuappTestDatalari.requestBodyOlustur();
        // 2- soruda varsa expected data olusturun

        JSONObject expectedData=herokuappTestDatalari.expectedDataOlustur();

        // 3- Response olusturun, request'i gonderip gelen response'u kaydedin

        Response response = given().
                                spec(specHerokuApp).
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                post("/{pp1}");

        // 4- Asserions

        JsonPath responseJsonPath = response.jsonPath();
        // "booking":{
        //                        "firstname":"Ahmet",
        assertEquals(expectedData.getJSONObject("booking").getString("firstname"),responseJsonPath.get("booking.firstname"));
        //                        "lastname":"Bulut",
        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),responseJsonPath.get("booking.lastname"));
        //                        "totalprice":500,
        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),responseJsonPath.getInt("booking.totalprice"));
        //                        "depositpaid":false,
        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),responseJsonPath.get("booking.depositpaid"));
        //                        "bookingdates":{
        //                            "checkin":"2021-06-01",
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),responseJsonPath.get("booking.bookingdates.checkin"));
        //                            "checkout":"2021-06-10"
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),responseJsonPath.get("booking.bookingdates.checkout"));
        //                        ,
        //                        "additionalneeds":"wi-fi"
        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));


    }
}
