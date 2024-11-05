package service;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import logger.Log;
import payloads.AuthorPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class AuthorsService {

    /**
     * Calls GET {baseUrl}/api/v1/Authors
     * Get all Authors
     * @param baseUrl the base url
     * @return list of all the deserialized Author payloads
     */
    public List<AuthorPayload> getAllAuthors(String baseUrl) {

        List<AuthorPayload> authorsList = new ArrayList<>();

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.AUTHORS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get all authors failed with status code: " + response.getStatusCode());
        }
        for (Object object : response.jsonPath().getList("")) {
            authorsList.add(UtilitiesClass.deserialize(object, AuthorPayload.class));
        }
        Log.info("Successful GET all Authors with response: \n" + response.getBody().asString());
        return authorsList;
    }

    /**
     * Calls GET {baseUrl}/api/v1/Authors/{id}
     * Get specific Author by id
     * @param baseUrl the base url
     * @param id the Author id
     * @return the deserialized Author payload
     */
    public AuthorPayload getAuthorById(String baseUrl, int id) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.AUTHORS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get author by id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful GET Author By Id with response: \n" + response.getBody().asString());
        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);
    }

    /**
     * Calls POST {baseUrl}/api/v1/Authors
     * Add Author
     * @param baseUrl the base url
     * @param authorPayload the Author payload
     * @return the deserialized Author payload
     */
    public AuthorPayload addAuthor(String baseUrl, AuthorPayload authorPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.post(baseUrl + Endpoint.AUTHORS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Add author with payload = " + authorPayload.toString() + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful POST Author with response: \n" + response.getBody().asString());
        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);

    }

    /**
     * Calls PUT {baseUrl}/api/v1/Authors/{id}
     * Update Author
     * @param baseUrl the base url
     * @param id the Author id
     * @param authorPayload the Author payload
     * @return the deserialized Author payload
     */
    public AuthorPayload updateAuthor(String baseUrl, int id, AuthorPayload authorPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.put(baseUrl + Endpoint.AUTHORS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Update author with id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful PUT Author with response: \n" + response.getBody().asString());

        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);
    }

    /**
     * Calls DELETE {baseUrl}/api/v1/Authors/{id}
     * Delete Author
     * @param baseUrl the base url
     * @param id the Author id
     * @return the response
     */
    public Response deleteAuthor(String baseUrl, int id) {
        return RestAssured.delete(baseUrl + Endpoint.AUTHORS.get() + "/" + id);
    }

}

