package testDataKlasoru;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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


    public Map tumBodyMapOlustur(String title,String body,double userId,double id){

        Map<String,Object> tumBodyMap=new HashMap();
        tumBodyMap.put("title",title);
        tumBodyMap.put("body",body);
        tumBodyMap.put("userId",userId);
        tumBodyMap.put("id",id);


        return tumBodyMap;
    }

    public Map requestBodyMapOlustur(){

        Map<String,Object> ReqBodyMap=new HashMap();
        ReqBodyMap.put("title","Ahmet");
        ReqBodyMap.put("body","Merhaba");
        ReqBodyMap.put("userId",10);
        ReqBodyMap.put("id",70);


        return ReqBodyMap;
    }

    public Map expectedBodyMapOlustur(){

        Map<String,Object> ExpectedBodyMap=new HashMap();
        ExpectedBodyMap.put("title","Ahmet");
        ExpectedBodyMap.put("body","Merhaba");
        ExpectedBodyMap.put("userId",10);
        ExpectedBodyMap.put("id",70);


        return ExpectedBodyMap;
    }

}
