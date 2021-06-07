package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C11_Get_ExpectedDataOlusturma {
    /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda
        donen response body’sinin asagida verilen ile ayni oldugunutest ediniz

        {
            "userId":3,
            "id":22,
            "title":"dolor sint quo a velit explicabo quia nam",
            "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }


     */

    @Test
    public void get01(){
        // 1- request icin url ve body

        String url ="https://jsonplaceholder.typicode.com/posts/22";

        // 2- Soruda isteniyorsa expected Data olustur

        JSONObject expectedDataJson=new JSONObject();
        expectedDataJson.put("userId",3);
        expectedDataJson.put("id",22);
        expectedDataJson.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDataJson.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        //3- Response olustur ve request'i yollayip sonucu response'a kaydet

        Response response=given().when().get(url);

        response.prettyPrint();

        // 4- Assertion

        // eger temel response bilgilerinin assert edilmesi isteniyorsa
        // daha once yaptigimiz metotla yapiyoruz
        // response.then().assertThat().statusCode(200);

        // expected data ile response'u karsilastirmak istersek
        // expected data icin JSONOBJECT metotlari ve
        // response icin JsonPATH kullanilir

        JsonPath responseJsonPath=response.jsonPath();
        Assert.assertEquals(expectedDataJson.get("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedDataJson.get("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedDataJson.get("body"),responseJsonPath.getString("body"));
        Assert.assertEquals(expectedDataJson.get("title"),responseJsonPath.getString("title"));
    }
}
