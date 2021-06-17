package test;

import baseUrlKlasoru.DummyRestApiBaseUrl;
import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.DummyRestApiTestDatalari;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Get_DeserializationIleTest extends DummyRestApiBaseUrl {
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
    public void Test01() {


        // 1- Request icin url ve body olustur
        specDummyRestApi.pathParams("pp1", "employee", "pp2", 3);


        // 2- Expected data olustur

        DummyRestApiTestDatalari dummyRestApiTestDatalari = new DummyRestApiTestDatalari();
        Map<String ,Object> expectedData = dummyRestApiTestDatalari.getExpectedDataOlusturMap();


        // 3- Response olustur, req gonder ve response'u kaydet
        Response response = given().spec(specDummyRestApi).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertion


        Map<String , Object> responseMap = response.as(HashMap.class);


        System.out.println(expectedData);
        // {
        //          data={
        //              profile_image=,
        //              employee_name=Ashton Cox,
        //              employee_salary=86000,
        //              id=3,
        //              employee_age=66
        //              },
        //          message=Successfully! Record has been fetched.,
        //          status=success
        //          }

        System.out.println(responseMap);
        //{
        //  data={
        //          id=3.0,
        //          employee_name=Ashton Cox,
        //          employee_salary=86000.0,
        //          employee_age=66.0,
        //          profile_image=
        //          },
        //  message=Successfully! Record has been fetched.,
        //  status=success
        //  }

        assertEquals(((Map)expectedData.get("data")).get("id"),((Map)responseMap.get("data")).get("id"));
        assertEquals(((Map)expectedData.get("data")).get("employee_name"),((Map)responseMap.get("data")).get("employee_name"));
        assertEquals(((Map)expectedData.get("data")).get("employee_salary"),((Map)responseMap.get("data")).get("employee_salary"));
        assertEquals(((Map)expectedData.get("data")).get("employee_age"),((Map)responseMap.get("data")).get("employee_age"));
        assertEquals(((Map)expectedData.get("data")).get("profile_image"),((Map)responseMap.get("data")).get("profile_image"));
        assertEquals(expectedData.get("message"),responseMap.get("message"));
        assertEquals(expectedData.get("status"),responseMap.get("status"));

    }
}
