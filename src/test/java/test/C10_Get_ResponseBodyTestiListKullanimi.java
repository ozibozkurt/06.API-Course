package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C10_Get_ResponseBodyTestiListKullanimi {
    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
    donen Response'in
    status code'unun 200,
    ve content type'inin Aplication.JSON,
    ve response body'sindeki
        employees sayisinin 24
	    ve employee'lerden birinin "Ashton Cox"
	    ve girilen yaslar icinde 61,20 ve 35 degerlerinin oldugunu test edin
    test edin.
     */


    @Test
    public void get01(){
        // 1- request icin url ve body hazirla
        String url="http://dummy.restapiexample.com/api/v1/employees";
        // 2- expected data hazirla (response'un tamami sorulmadigi icin expected data hazirlamamiza gerek yok)
        // 3- Server'dan gelen response bilgilerini al

        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- expected data ile actual data'yi karsilastirip asertion yap
        // response'a ait bilgileri assertThat() metodu ile assert edebiliriz


        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasSize(24)).
                body("data.employee_name",hasItem("Ashton Cox")).
                body("data.employee_age",hasItems(61,21,35));

    }
}
