package test;

import baseUrlKlasoru.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Data;
import pojos.PojoDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_Get_Pojo extends DummyRestApiBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine
    bir GET request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

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

        // 2- soruda varsa expected Data olustur

        Data dummyDataPojo = new Data(3,"Ashton Cox",86000,66,"");
        PojoDummy expectedDataPojo=new PojoDummy("success",dummyDataPojo,"Successfully! Record has been fetched.");

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().spec(specDummyRestApi).when().get("/{pp1}/{pp2}");
        response.prettyPrint();
        // 4- Assertions
        // expectedDataPojo ==> response
        PojoDummy responsePojo=response.as(PojoDummy.class);
        JsonPath responseJsonPath=response.jsonPath();

        // expectedDataPojo ==> responsePojo
        System.out.println(responsePojo);
        assertEquals(expectedDataPojo.getStatus(),responsePojo.getStatus());
        assertEquals(expectedDataPojo.getData().getId(),responseJsonPath.get("data.id"));
        assertEquals(expectedDataPojo.getData().getEmployeeName(),responseJsonPath.get("data.employee_name"));
        assertEquals(expectedDataPojo.getData().getEmployeeSalary(),responseJsonPath.get("data.employee_salary"));
        assertEquals(expectedDataPojo.getData().getEmployeeAge(),responseJsonPath.get("data.employee_age"));
        assertEquals(expectedDataPojo.getData().getProfileImage(),responseJsonPath.get("data.profile_image"));
        assertEquals(expectedDataPojo.getMessage(),responsePojo.getMessage());
    }
}
