package common.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.constants.SerializationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilitiesClass {

    /**
     * Add Headers in request
     * @return request with headers
     */
    public static RequestSpecification getRequestWithHeader(){
        RequestSpecification request = RestAssured.given();
        request.header(SerializationProperties.CONTENT_TYPE, SerializationProperties.APPLICATION_JSON);
        return request;
    }

    /**
     * Deserialize response
     * @param object the object
     * @param type the payload class
     * @return deserialized Object
     */
    public static <T> T deserialize(Object object, Class<T> type) {
        return new ObjectMapper().convertValue(object, type);
    }

    /**
     * Check if response status code is 200
     * @param response the response
     * @return true is response status code is 200
     */
    public static boolean isHttpResponseStatusCodeExpected(Response response) {
        return response.getStatusCode() == 200;
    }


}
