package test;

import baseUrlKlasoru.HerokuAppBaseUrl;
import baseUrlKlasoru.JsonPlaceBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataKlasoru.JsonplaceholderTestDatalari;

import static io.restassured.RestAssured.given;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda donen response’in status kodunun 200
        ve response body’sinin asagida verilen ile ayni oldugunutest ediniz

        Response body :
        {
            "userId":3,
            "id":22,
            "title":"dolor sint quo a velit explicabo quia nam",
            "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */

    @Test
    public void test01(){
        // 1- Requst url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",22);
        // 2- Soruda varsa expected data olustur

        JsonplaceholderTestDatalari jsonplaceholderTestDatalari=new JsonplaceholderTestDatalari();
        JSONObject expectedDataBody=jsonplaceholderTestDatalari.getRequestExpectedBody();

        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                                spec(specJsonPlace).
                                when().
                                get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertions
        JsonPath jsonPathResponse=response.jsonPath();

        // Hangi yontemle asser edeceginize karar verin

        Assert.assertEquals(jsonplaceholderTestDatalari.basariliStatusCode,response.statusCode());
        Assert.assertEquals(expectedDataBody.get("userId"),jsonPathResponse.getInt("userId"));
        Assert.assertEquals(expectedDataBody.get("id"),jsonPathResponse.getInt("id"));
        Assert.assertEquals(expectedDataBody.get("title"),jsonPathResponse.getString("title"));
        Assert.assertEquals(expectedDataBody.get("body"),jsonPathResponse.getString("body"));
    }
}
