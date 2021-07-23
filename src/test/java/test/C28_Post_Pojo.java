package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PojoBookingBody;
import pojos.PojoBookingDates;
import pojos.PojoBookingResponseBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Post_Pojo extends HerokuAppBaseUrl {
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
    public void test01(){
        // 1- Request url ve body olustur
        specHerokuApp.pathParam("pp1","booking");
        PojoBookingDates reqBodyBookingDates=new PojoBookingDates("2021-06-01","2021-06-10");
        PojoBookingBody requestBodyPojo=new PojoBookingBody("Ahmet","Bulut",500,false,reqBodyBookingDates,"wi-fi");

        // 2- soruda varsa expected Data olustur

        PojoBookingDates expectedDataBookingDates=new PojoBookingDates("2021-06-01","2021-06-10");
        PojoBookingBody expectedDataBookingBodyPojo=new PojoBookingBody("Ahmet","Bulut",500,false,reqBodyBookingDates,"wi-fi");
        PojoBookingResponseBody expectedDataPojo=new PojoBookingResponseBody(24,expectedDataBookingBodyPojo);


        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                spec(specHerokuApp).
                contentType(ContentType.JSON).
                body(requestBodyPojo).
                when().
                post("/{pp1}");

        response.prettyPrint();

        // 4- Assertions
        // expectedDataPojo ==> response

        PojoBookingResponseBody responsePojo=response.as(PojoBookingResponseBody.class);
        // expectedDataPojo ==> responsePojo
        assertEquals(expectedDataPojo.getBooking().getFirstname(),responsePojo.getBooking().getFirstname());
        assertEquals(expectedDataPojo.getBooking().getLastname(),responsePojo.getBooking().getLastname());
        assertEquals(expectedDataPojo.getBooking().getTotalprice(),responsePojo.getBooking().getTotalprice());
        assertEquals(expectedDataPojo.getBooking().isDepositpaid(),responsePojo.getBooking().isDepositpaid());
        assertEquals(expectedDataPojo.getBooking().getAdditionalneeds(),responsePojo.getBooking().getAdditionalneeds());
        assertEquals(expectedDataPojo.getBooking().getBookingdates().getCheckin(),responsePojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBooking().getBookingdates().getCheckout(),responsePojo.getBooking().getBookingdates().getCheckout());
    }
}
