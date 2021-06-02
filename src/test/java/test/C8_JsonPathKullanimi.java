package test;

import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C8_JsonPathKullanimi {
    @Test
    public void JsonPath01(){
        /*
              {
                  "firstName": "Ahmet",
                  "lastName" : "Bulut",
                  "age"      : 49,
                  "address"  : {
                    "streetAddress": "Kurtulus cad.",
                    "city"         : "Ankara",
                    "postalCode"   : "06100"
                  },
                  "phoneNumbers": [
                    {
                      "type"  : "cep",
                      "number": "532-555 55 55"
                    },
                    {
                      "type"  : "ev",
                      "number": "0312-123 4567"
                    }
                  ]
                }
         */


        JSONObject kisiBilgisi = new JSONObject();
        JSONObject adres=new JSONObject();
        JSONArray arrTel = new JSONArray();
        JSONObject telCep=new JSONObject();
        JSONObject telEv=new JSONObject();

        telEv.put("type", "ev");
        telEv.put("number", "0312-123 4567");

        telCep.put("type", "cep");
        telCep.put("number", "532-555 55 55");

        arrTel.put(telCep);
        arrTel.put(telEv);


        adres.put( "streetAddress", "Kurtulus cad.");
        adres.put( "city", "Ankara");
        adres.put( "postalCode","06100");

        kisiBilgisi.put("firstName", "Ahmet");
        kisiBilgisi.put("lastName", "Bulut");
        kisiBilgisi.put("age", 49);
        kisiBilgisi.put("address",adres);
        kisiBilgisi.put("phoneNumbers",arrTel);

        System.out.println(kisiBilgisi);

        System.out.println("isim : " + kisiBilgisi.get("firstName"));
        System.out.println("Soyisim : " + kisiBilgisi.get("lastName"));
        System.out.println("yas : " + kisiBilgisi.get("age"));
        System.out.println("adres : " + kisiBilgisi.get("address").toString());
        System.out.println("sehir : " + kisiBilgisi.getJSONObject("address").get("city"));
        System.out.println("Tel numaralari : " + kisiBilgisi.getJSONArray("phoneNumbers"));
        System.out.println("cep telefonu : " + kisiBilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));
     }
}
