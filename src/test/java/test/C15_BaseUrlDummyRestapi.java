package test;

import baseUrlKlasoru.DummyRestapiBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlDummyRestapi extends DummyRestapiBaseUrl {

    @Test
    public void test01(){
        // 1-  http://dummy.restapiexample.com/api/v1/employees endpointine
        // bir GET request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve Response’ta 24 calisanin oldugunu test edin

        // 1- Request url ve body olustur
        specDummy.pathParam("Pp1","employees");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonderip donen sonucu kaydet
        Response response=given().spec(specDummy).when().get("/{Pp1}");
        response.prettyPrint();
        // 4- Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("data.id", Matchers.hasSize(24));

    }

    @Test
    public void test02(){
        //2- http://dummy.restapiexample.com/api/v1/employee/1 endpointine
        // bir GET request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve “employee_name” degerinin “Tiger Nixon” oldugunu test edin

        // 1- Requst url ve body olustur
        specDummy.pathParams("Pp1","employee","Pp2",1);

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().spec(specDummy).when().get("/{Pp1}/{Pp2}");
        response.prettyPrint();
        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("data.employee_name",Matchers.equalTo("Tiger Nixon"));
    }

    @Test
    public void test03(){
        // 3- http://dummy.restapiexample.com/api/v1/delete/2 endpointine
        // bir DELETE request gonderdigimizde donen response’un
        // status code’unun 200 oldugunu ve "message” degerinin "Successfully! Record has been deleted” oldugunu test edin

        // 1- Requst url ve body olustur
        specDummy.pathParams("Pp1","delete","Pp2",2);
        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().spec(specDummy).when().delete("/{Pp1}/{Pp2}");
        response.prettyPrint();

        // Assertions
        response.
                then().
                assertThat().
                statusCode(200).
                body("message",Matchers.equalTo("Successfully! Record has been deleted"));
    }

}
