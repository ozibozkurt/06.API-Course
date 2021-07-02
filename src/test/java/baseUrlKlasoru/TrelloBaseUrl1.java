package baseUrlKlasoru;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TrelloBaseUrl1 {

    protected RequestSpecification specTrello;

    @Before
    public void setup(){
        specTrello=new RequestSpecBuilder().setBaseUri("https://api.trello.com").build();
    }
}
