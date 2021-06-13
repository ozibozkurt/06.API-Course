package testDataKlasoru;

import org.json.JSONObject;

public class JsonplaceholderTestDatalari {

   public int basariliStatusCode = 200;
   public String contentType="application/json; charset=utf-8";
   public String headerConnection="keep-alive";


    public JSONObject getRequestExpectedBody() {
        JSONObject expectedDataJson=new JSONObject();
        expectedDataJson.put("userId",3);
        expectedDataJson.put("id",22);
        expectedDataJson.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDataJson.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return expectedDataJson;
    }

    public JSONObject putRequestBody() {
        JSONObject RequestBody=new JSONObject();
        RequestBody.put("userId",10);
        RequestBody.put("id",70);
        RequestBody.put("title","Ahmet");
        RequestBody.put("body","Merhaba");

        return RequestBody;
    }

    public JSONObject putRequestExpectedBody() {
        JSONObject putRequestExpectedBodyJson=new JSONObject();
        putRequestExpectedBodyJson.put("userId",10);
        putRequestExpectedBodyJson.put("id",70);
        putRequestExpectedBodyJson.put("title","Ahmet");
        putRequestExpectedBodyJson.put("body","Merhaba");

        return putRequestExpectedBodyJson;
    }




}
