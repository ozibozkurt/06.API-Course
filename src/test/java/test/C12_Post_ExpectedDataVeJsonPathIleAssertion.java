package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;



import static io.restassured.RestAssured.given;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {
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
    public void postTest(){
        // 1- request icin url ve body hazirla
        String url = "https://restful-booker.herokuapp.com/booking";
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

        // 2- soruda varsa expected data olusturun

        JSONObject expectedData=new JSONObject();
        JSONObject bookingBody = new JSONObject();
        JSONObject bookingdatesBody=new JSONObject();

        bookingdatesBody.put("checkin","2021-06-01");
        bookingdatesBody.put("checkout","2021-06-10");

        bookingBody.put("firstname","Ahmet");
        bookingBody.put("lastname","Bulut");
        bookingBody.put("totalprice",500);
        bookingBody.put("depositpaid",false);
        bookingBody.put("bookingdates",bookingdatesBody);
        bookingBody.put("additionalneeds","wi-fi");

        expectedData.put("bookingid",24);
        expectedData.put("booking",bookingBody);

        // 3- Response olusturun, request'i gonderip gelen response'u kaydedin

        Response response =given().
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                post(url);

        // 4- Asserion
        //    assertThat() ile yaparsak response icin bir sey yapmamiza gerek yok
        //    ama Assert.assertEqual() kullanacaksak response'u JsonPath objesine cevirmeliyiz

        JsonPath responseJsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.getJSONObject("booking").get("firstname"),responseJsonPath.get("booking.firstname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("lastname"),responseJsonPath.get("booking.lastname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("totalprice"),responseJsonPath.get("booking.totalprice"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),responseJsonPath.get("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),responseJsonPath.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),responseJsonPath.get("booking.bookingdates.checkout"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));
    }


}
