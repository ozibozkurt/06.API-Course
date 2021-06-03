package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C09_Post_JsonPathIleBodyTesti {
     /*
    https://restful-booker.herokuapp.com/booking/10 url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde
               {
            "firstname" : "Ahmet",
            "lastname" : "Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
            },
            "additionalneeds" : "wi-fi"
        }
    donen Response’un,
 	status code’unun 200,
	ve content type’inin application-json,
	ve response body’sindeki
    	"firstname“in,"Ahmet",
    	ve "lastname“in, "Bulut",
		ve "totalprice“in,500,
		ve "depositpaid“in,false,
		ve "checkin" tarihinin 2021-06-01
		ve "checkout" tarihinin 2021-06-10
		ve "additionalneeds“in,"wi-fi"

    oldugunu test edin

     */

    @Test
    public void get01() {
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

        System.out.println(requestBody);
        System.out.println(requestBody.get("bookingdates"));
        System.out.println(requestBody.get("firstname"));
        System.out.println(requestBody.get("depositpaid"));
        System.out.println(requestBody.getJSONObject("bookingdates").get("checkin"));

        // 2- expected data hazirla (response'un tamami sorulmadigi icin expected data hazirlamamiza gerek yok)
        // 3- Server'dan gelen response bilgilerini al

        Response response = given().
                            contentType(ContentType.JSON).
                            when().
                            body(requestBody.toString()).
                            post(url);
        response.prettyPrint();

        // 4- expected data ile actual data'yi karsilastirip asertion yap
        // response'a ait bilgileri assertThat() metodu ile assert edebiliriz

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("booking.firstname", equalTo("Ahmet"),
                        "booking.lastname",equalTo("Bulut"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2021-06-01"),
                        "booking.bookingdates.checkout",equalTo("2021-06-10"));
    }
}
