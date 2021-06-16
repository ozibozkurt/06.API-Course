package testDataKlasoru;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

    public class HerokuappTestDatalari {

    public JSONObject requestBodyOlustur(){
        JSONObject requestBody = new JSONObject();
        JSONObject innerBody = new JSONObject();

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);

        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");
        requestBody.put("bookingdates", innerBody);

        requestBody.put("additionalneeds", "wi-fi");
        return requestBody;

    }

    public JSONObject expectedDataOlustur(){
        JSONObject expectedData = new JSONObject();
        JSONObject bookingBody = new JSONObject();
        JSONObject bookingdatesBody = new JSONObject();

        bookingdatesBody.put("checkin", "2021-06-01");
        bookingdatesBody.put("checkout", "2021-06-10");

        bookingBody.put("firstname", "Ahmet");
        bookingBody.put("lastname", "Bulut");
        bookingBody.put("totalprice", 500);
        bookingBody.put("depositpaid", false);
        bookingBody.put("bookingdates", bookingdatesBody);
        bookingBody.put("additionalneeds", "wi-fi");

        expectedData.put("bookingid", 24);
        expectedData.put("booking", bookingBody);
        return expectedData;
    }

    public Map<String,Object> postRequestBodyOlusturMap(){
        Map<String,Object> requestBody=new HashMap<>();
        Map<String ,String> bookingBody=new HashMap<>();

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);

        bookingBody.put("checkin", "2021-06-01");
        bookingBody.put("checkout", "2021-06-10");
        requestBody.put("bookingdates", bookingBody);

        requestBody.put("additionalneeds", "wi-fi");
        return requestBody;
    }

        public Map<String,Object> postExpectedDataOlusturMap(){
            Map<String,Object> expectedData = new HashMap<>();
            Map<String,Object> bookingBody = new HashMap<>();
            Map<String,Object> bookingdatesBody = new HashMap<>();

            bookingdatesBody.put("checkin", "2021-06-01");
            bookingdatesBody.put("checkout", "2021-06-10");

            bookingBody.put("firstname", "Ahmet");
            bookingBody.put("lastname", "Bulut");
            bookingBody.put("totalprice", 500);
            bookingBody.put("depositpaid", false);
            bookingBody.put("bookingdates", bookingdatesBody);
            bookingBody.put("additionalneeds", "wi-fi");

            expectedData.put("bookingid", 24);
            expectedData.put("booking", bookingBody);
            return expectedData;
        }






}
