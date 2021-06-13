package baseUrlKlasoru;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyRestApiBaseUrl {

    protected RequestSpecification specDummyRestApi;


    @Before
    public void setup(){
        specDummyRestApi=new RequestSpecBuilder().
                                            setBaseUri("http://dummy.restapiexample.com/api/v1").
                                            build();
    }
}
