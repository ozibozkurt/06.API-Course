package test;

import baseUrlKlasoru.TrelloBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C27_TrelloDeserialization extends TrelloBaseUrl {
    // https://api.trello.com/1/boards/?key={{TrelloKey}}&token={{TrelloToken}}&name=Api Board

    @Test
    public void test01(){
        specTrello.
                pathParams("pp1",1,"pp2","boards").
                queryParams("key","df524c4a1d9e349a462db5b5f081bfe9","token","cef4b32278ac66e68a5426ba92fcc08b58a95b22488f111e5589c6aed9ace0bc","name","api board3");


        Response response=given().spec(specTrello).contentType(ContentType.JSON).when().post("/{pp1}/{pp2}/");
        response.prettyPrint();

        String expectedDataJson= "{\n" +
                "    \"id\": \"60cef76ce3e9721060e0cd2c\",\n" +
                "    \"name\": \"Api Board\",\n" +
                "    \"desc\": \"\",\n" +
                "    \"descData\": null,\n" +
                "    \"closed\": false,\n" +
                "    \"idOrganization\": \"608bb1f8c74f6f3f28e98a67\",\n" +
                "    \"idEnterprise\": null,\n" +
                "    \"pinned\": false,\n" +
                "    \"url\": \"https://trello.com/b/9Vcvtamh/api-board\",\n" +
                "    \"shortUrl\": \"https://trello.com/b/9Vcvtamh\",\n" +
                "    \"prefs\": {\n" +
                "        \"permissionLevel\": \"private\",\n" +
                "        \"hideVotes\": false,\n" +
                "        \"voting\": \"disabled\",\n" +
                "        \"comments\": \"members\",\n" +
                "        \"invitations\": \"members\",\n" +
                "        \"selfJoin\": true,\n" +
                "        \"cardCovers\": true,\n" +
                "        \"isTemplate\": false,\n" +
                "        \"cardAging\": \"regular\",\n" +
                "        \"calendarFeedEnabled\": false,\n" +
                "        \"background\": \"blue\",\n" +
                "        \"backgroundImage\": null,\n" +
                "        \"backgroundImageScaled\": null,\n" +
                "        \"backgroundTile\": false,\n" +
                "        \"backgroundBrightness\": \"dark\",\n" +
                "        \"backgroundColor\": \"#0079BF\",\n" +
                "        \"backgroundBottomColor\": \"#0079BF\",\n" +
                "        \"backgroundTopColor\": \"#0079BF\",\n" +
                "        \"canBePublic\": true,\n" +
                "        \"canBeEnterprise\": true,\n" +
                "        \"canBeOrg\": true,\n" +
                "        \"canBePrivate\": true,\n" +
                "        \"canInvite\": true\n" +
                "    },\n" +
                "    \"labelNames\": {\n" +
                "        \"green\": \"\",\n" +
                "        \"yellow\": \"\",\n" +
                "        \"orange\": \"\",\n" +
                "        \"red\": \"\",\n" +
                "        \"purple\": \"\",\n" +
                "        \"blue\": \"\",\n" +
                "        \"sky\": \"\",\n" +
                "        \"lime\": \"\",\n" +
                "        \"pink\": \"\",\n" +
                "        \"black\": \"\"\n" +
                "    },\n" +
                "    \"limits\": {}\n" +
                "}" ;


        Gson gson=new Gson();



       Map<String,Object> responseMap = JsonUtil.convertJsonToJava(expectedDataJson, HashMap.class);
       System.out.println(responseMap);

    }



}
