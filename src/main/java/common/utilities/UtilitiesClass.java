package common.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.constants.SerializationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilitiesClass {
    public static RequestSpecification getRequestWithHeader(){
        RequestSpecification request = RestAssured.given();
        request.header(SerializationProperties.CONTENT_TYPE, SerializationProperties.APPLICATION_JSON);
        return request;
    }

    public static <T> T deserialize(Object object, Class<T> type) {
        return new ObjectMapper().convertValue(object, type);
    }

    public static boolean isHttpResponseStatusCodeExpected(Response response) {
        return response.getStatusCode() == 200;
    }
}
