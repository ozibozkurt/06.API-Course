package test;

import baseUrlKlasoru.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.DummyRestApiTestDatalari;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyRestApiBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
    donen response’un status code’unun 200, content Type’inin application/json
    ve body’sinin asagidaki gibi oldugunu test edin.
        Response Body
        {
            "status":"success",
            "data":{
                    "id":3,
                    "employee_name":"Ashton Cox",
                    "employee_salary":86000,
                    "employee_age":66,
                    "profile_image":""
                    },
            "message":"Successfully! Record has been fetched."
        }
     */

    @Test
    public void Test01(){



    // 1- Request icin url ve body olustur
    specDummyRestApi.pathParams("pp1","employee","pp2",3);


    // 2- Expected data olustur

        DummyRestApiTestDatalari dummyRestApiTestDatalari=new DummyRestApiTestDatalari();
        JSONObject expectedData=dummyRestApiTestDatalari.getExpectedDataOlustur();


    // 3- Response olustur, req gonder ve response'u kaydet
    Response response = given().spec(specDummyRestApi).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

    // 4- Assertion
    JsonPath responseJPath=response.jsonPath();

        // status code’unun 200,

        assertEquals(dummyRestApiTestDatalari.basariliStatusCode,response.getStatusCode());
        // content Type’inin JSON
        assertEquals(dummyRestApiTestDatalari.contentType,response.getContentType());
        //        {
        //            "status":"success",
        assertEquals(expectedData.get("status"),responseJPath.getString("status"));
        //            "data":{
        //                    "id":3,
        assertEquals(expectedData.getJSONObject("data").get("id"),responseJPath.get("data.id"));
        //                    "employee_name":"Ashton Cox",
        assertEquals(expectedData.getJSONObject("data").get("employee_name"),responseJPath.getString("data.employee_name"));
        //                    "employee_salary":86000,
        assertEquals(expectedData.getJSONObject("data").get("employee_salary"),responseJPath.getInt("data.employee_salary"));
        //                    "employee_age":66,
        assertEquals(expectedData.getJSONObject("data").get("employee_age"),responseJPath.getInt("data.employee_age"));
        //                    "profile_image":""
        assertEquals(expectedData.getJSONObject("data").get("profile_image"),responseJPath.get("data.profile_image"));
        //                    },
        //            "message":"Successfully! Record has been fetched."
        assertEquals(expectedData.getString("message"),responseJPath.getString("message"));
        //        }



    }
}
