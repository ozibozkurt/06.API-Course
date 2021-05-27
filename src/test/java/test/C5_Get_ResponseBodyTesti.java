package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;



import static io.restassured.RestAssured.given;

public class C5_Get_ResponseBodyTesti {
    /*
    https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
    donen Response'in
    status code'unun 200,
    ve content type'inin Aplication.JSON,
    ve response body'sinde bulunan userId'nin 5,
    ve response body'sinde bulunan title'in "optio dolor molestias sit"
    oldugunu test edin.
     */


    @Test
    public void get01(){
        // 1- request icin url ve body hazirla
        String url="https://jsonplaceholder.typicode.com/posts/44";
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
                body("title",Matchers.equalTo("optio dolor molestias sit")).
                body("userId",Matchers.equalTo(5));

    }
}
