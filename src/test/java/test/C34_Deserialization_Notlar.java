package test;

import baseUrlKlasoru.DummyRestApiBaseUrl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.Test;
import testDataKlasoru.DummyRestApiTestDatalari;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C34_Deserialization_Notlar extends DummyRestApiBaseUrl {
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

        // Response'u Json objesi yapmak icin
        System.out.println(response.asString().toUpperCase());
        String responseString=response.asString();

        Gson gson=new Gson();



    }


}
