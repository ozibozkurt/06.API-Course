package test;

import baseUrlKlasoru.DummyRestApiBaseUrl;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.DummyRestApiTestDatalari;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26__Get_De_Serialization extends DummyRestApiBaseUrl {
        /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.
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
    public void test01(){
        // 1- Request url ve body olustur
        specDummyRestApi.pathParams("pp1","employee","pp2",3);

        // 2- soruda varsa expected data olustur

        DummyRestApiTestDatalari dummyRestApiTestDatalari=new DummyRestApiTestDatalari();
        HashMap<String,Object> expectedDataMap=dummyRestApiTestDatalari.tumBodyMapOlustur("success",
                3,"Ashton Cox",86000,66,"",
                "Successfully! Record has been fetched.");

        System.out.println(expectedDataMap);

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                                spec(specDummyRestApi).
                                when().
                                get("/{pp1}/{pp2}");

        response.prettyPrint();
        // 4- Assert
        HashMap<String,Object> responseMap=response.as(HashMap.class);

        assertEquals(expectedDataMap.get("status"),responseMap.get("status"));
        assertEquals(((Map)expectedDataMap.get("data")).get("id"),((Map)responseMap.get("data")).get("id"));
        assertEquals(((Map)expectedDataMap.get("data")).get("employee_name"),((Map)responseMap.get("data")).get("employee_name"));
        assertEquals(((Map)expectedDataMap.get("data")).get("employee_salary"),((Map)responseMap.get("data")).get("employee_salary"));
        assertEquals(((Map)expectedDataMap.get("data")).get("employee_age"),((Map)responseMap.get("data")).get("employee_age"));
        assertEquals(((Map)expectedDataMap.get("data")).get("profile_image"),((Map)responseMap.get("data")).get("profile_image"));
        assertEquals(expectedDataMap.get("message"),responseMap.get("message"));

    }


}
