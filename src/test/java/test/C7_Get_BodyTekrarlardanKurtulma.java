package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class C7_Get_BodyTekrarlardanKurtulma {

    /*
    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
 	status code’unun 200,
	ve content type’inin application-json,
	ve response body’sindeki
    	"firstname“in,"Susan",
    	ve "lastname“in, "Jackson",
		ve "totalprice“in,612,
		ve "depositpaid“in,false,
		ve "additionalneeds“in,"Breakfast"
    oldugunu test edin

     */

    @Test
    public void get01(){
        // 1- request icin url ve body hazirla
        String url="https://restful-booker.herokuapp.com/booking/10";
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
                body("firstname", equalTo("Susan")).
                body("lastname", equalTo("Wilson")).
                body("totalprice", equalTo(643)).
                body("depositpaid", equalTo(false)).
                body("additionalneeds", equalTo(null));

        // tekrarli body yazilarindan kurtulmak icin
        // body ile ilgili tum assertion'lar tek body parantezi icinde yapilip virgulle ayrilabilir
        /*
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",Matchers.equalTo("Susan"),
                        "lastname",Matchers.equalTo("Wilson"),
                        "totalprice",Matchers.equalTo(643),
                        "depositpaid",Matchers.equalTo(false),
                        "additionalneeds",Matchers.equalTo(null));
        */

        // tekrarli Matchers'lardan kurtulmak icin
        // Matchers Class'indan static metodlari import edebiliriz

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Susan"),
                        "lastname", equalTo("Wilson"),
                        "totalprice", equalTo(643),
                        "depositpaid", equalTo(false),
                        "additionalneeds", equalTo(null));
    }
}
