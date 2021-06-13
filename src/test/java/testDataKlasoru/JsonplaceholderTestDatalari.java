package testDataKlasoru;

import org.json.JSONObject;

public class JsonplaceholderTestDatalari {

   public int basariliStatusCode = 200;


    public JSONObject getRequestExpectedBody() {
        JSONObject expectedDataJson=new JSONObject();
        expectedDataJson.put("userId",3);
        expectedDataJson.put("id",22);
        expectedDataJson.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDataJson.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return expectedDataJson;
    }
}
