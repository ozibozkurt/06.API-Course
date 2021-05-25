package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C1_Get_ApiSorgulama {


    @Test
    public void get01(){
        String url ="https://restful-booker.herokuapp.com/booking/10";
        Response response = given().when().get(url);
        response.prettyPrint();

        System.out.println("status code : " + response.getStatusCode());
        System.out.println("Content Type : " + response.getContentType());
        System.out.println("Header Server : "+response.getHeader("Server"));
        System.out.println("Status Line : "+ response.getStatusLine());
        System.out.println("Time : "+ response.getTime());



    }
}
