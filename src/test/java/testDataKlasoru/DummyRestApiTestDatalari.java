package testDataKlasoru;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DummyRestApiTestDatalari {

    public int basariliStatusCode=200;
    public String contentType="application/json";

    public JSONObject getExpectedDataOlustur(){
        JSONObject expectedData = new JSONObject();
        JSONObject dataBody=new JSONObject();
        dataBody.put("id",3);
        dataBody.put("employee_name", "Ashton Cox");
        dataBody.put("employee_salary",86000);
        dataBody.put("employee_age",66);
        dataBody.put("profile_image","");

        expectedData.put("status","success");
        expectedData.put("data",dataBody);
        expectedData.put("message","Successfully! Record has been fetched.");
        return expectedData;
    }

    public Map<String,Object> getExpectedDataOlusturMap(){
        Map<String,Object> expectedDataMap = new HashMap<>();
        Map<String,Object> dataBody=new HashMap<>();
        dataBody.put("id",3.0);
        dataBody.put("employee_name", "Ashton Cox");
        dataBody.put("employee_salary",86000.0);
        dataBody.put("employee_age",66.0);
        dataBody.put("profile_image","");

        expectedDataMap.put("status","success");
        expectedDataMap.put("data",dataBody);
        expectedDataMap.put("message","Successfully! Record has been fetched.");
        return expectedDataMap;
    }
}
