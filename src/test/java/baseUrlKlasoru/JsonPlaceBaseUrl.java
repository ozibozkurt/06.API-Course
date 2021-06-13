package baseUrlKlasoru;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceBaseUrl {

    protected RequestSpecification specJsonPlace;
    @Before
    public void setup(){
        specJsonPlace= new RequestSpecBuilder().
                                        setBaseUri("https://jsonplaceholder.typicode.com").
                                        build();
    }
}
