package test;

import org.json.JSONObject;
import org.junit.Test;

public class C3_JsonObjesiOlusturma {
    @Test
    public void JsonObje(){

        /*
               {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 1
              }
         */

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title", "Ahmet");
        jsonObject.put("body", "Merhaba");
        jsonObject.put("userId", 1);

        System.out.println("");
        System.out.println(jsonObject.toString());
    }

    @Test
    public void JsonObje2(){
        /*
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }
         */

        JSONObject jsonObjectInner=new JSONObject();
        jsonObjectInner.put("checkin","2018-01-01");
        jsonObjectInner.put("checkout","2019-01-01");

        JSONObject jsonObjectBody=new JSONObject();
        jsonObjectBody.put("firstname","Jim");
        jsonObjectBody.put("lastname","Brown");
        jsonObjectBody.put("totalprice",111);
        jsonObjectBody.put("depositpaid",true);
        jsonObjectBody.put("bookingdates",jsonObjectInner);
        jsonObjectBody.put("additionalneeds","Breakfast");

        System.out.println("");
        System.out.println(jsonObjectBody.toString());


    }
}
