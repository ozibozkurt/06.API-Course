package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;



import static io.restassured.RestAssured.given;

public class C22_BaseUrlKullanimi extends HerokuAppBaseUrl {

    @Test
    public void get01(){

    // https://restful-booker.herokuapp.com/booking/10

        specHerokuApp.pathParams("pp1","booking","pp2",10);

        Response response =given().spec(specHerokuApp).when().get("/{pp1}/{pp2}");

        response.prettyPrint();
}
}
