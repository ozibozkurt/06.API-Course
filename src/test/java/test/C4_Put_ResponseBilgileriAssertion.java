package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;



import static io.restassured.RestAssured.given;

public class C4_Put_ResponseBilgileriAssertion {
    /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                 "userId":10,
                 "id":70
                }
        donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
     */

    @Test
    public void put01(){
        // 1- request icin url ve body hazirla
        String url = "https://jsonplaceholder.typicode.com/posts/70 ";
        JSONObject requestBody= new JSONObject();
        requestBody.put("title","Ahmet");
        requestBody.put("body","Merhaba");
        requestBody.put("userId",10);
        requestBody.put("id",70);
        // 2- expected data hazirla
        // 3- Server'dan gelen response bilgilerini al
        Response response= given().
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                put(url);
        response.prettyPrint();
        // 4- expected data ile actual data'yi karsilastirip asertion yap
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server","cloudflare").
                statusLine("HTTP/1.1 200 OK");
    }
}
