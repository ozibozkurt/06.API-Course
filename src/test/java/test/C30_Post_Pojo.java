package test;

import baseUrlKlasoru.TrelloBaseUrl1;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.HavaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C30_Post_Pojo extends TrelloBaseUrl1 {
    /*
     https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0 url’ine
     bir post request gonderdigimizde donen response’un asagidaki body’ye sahip oldugunu test ediniz

                        {
                    "coord": {
                        "lon": -0.1257,
                        "lat": 51.5085
                    },
                    "weather": [
                        {
                            "id": 804,
                            "main": "Clouds",
                            "description": "overcast clouds",
                            "icon": "04d"
                        }
                    ],
                    "base": "stations",
                    "main": {
                        "temp": 291.99,
                        "feels_like": 292.18,
                        "temp_min": 289.89,
                        "temp_max": 293.71,
                        "pressure": 1007,
                        "humidity": 86
                    },
                    "visibility": 8000,
                    "wind": {
                        "speed": 1.54,
                        "deg": 0
                    },
                    "clouds": {
                        "all": 90
                    },
                    "dt": 1627206846,
                    "sys": {
                        "type": 2,
                        "id": 2006068,
                        "country": "GB",
                        "sunrise": 1627186441,
                        "sunset": 1627243183
                    },
                    "timezone": 3600,
                    "id": 2643743,
                    "name": "London",
                    "cod": 200
                }
     */

    @Test
    public void test01(){
        // 1- Request url ve body olustur
        // 2- soruda varsa expected Data olustur

        Coord coordPojo=new Coord(-0.1257F,51.5085F);
        Weather weatherPojo=new Weather(804,"Clouds","overcast clouds","04d");
        List<Weather> weatherList=new ArrayList<>();
        weatherList.add(weatherPojo);
        Main mainPojo=new Main(291.99F,292.18F,289.89F,293.71F,1007,86);
        Wind windPojo=new Wind(1.54F,0);
        Clouds cloudsPojo=new Clouds(90);
        Sys sysPojo=new Sys(2,2006068,"GB",1627186441,1627243183);

        PojoHavaDurumu expectedData=new PojoHavaDurumu(coordPojo,weatherList,"stations",mainPojo,8000,windPojo,cloudsPojo,1627206846,sysPojo,3600,2643743,"London",200);

        System.out.println(expectedData);

        // 3- Response olustur, request gonderip donen response'i kaydet
        Response response=given().when().get("https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");
        response.prettyPrint();

        //4- Assertions
        JsonPath responseJPath = response.jsonPath();

        Assert.assertEquals(expectedData.getBase(),responseJPath.get("base"));
        Assert.assertEquals(expectedData.getSys().getId(),responseJPath.get("sys.id"));

    }
}
