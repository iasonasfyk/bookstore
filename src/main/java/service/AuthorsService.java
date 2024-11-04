package service;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import payloads.AuthorPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class AuthorsService {

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
        return authorsList;
    }

    public AuthorPayload getAuthorById(String baseUrl, int id) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.AUTHORS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get author by id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);
    }

    public AuthorPayload addAuthor(String baseUrl, AuthorPayload authorPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.post(baseUrl + Endpoint.AUTHORS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Add author with payload = " + authorPayload.toString() + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);

    }

    public AuthorPayload updateAuthor(String baseUrl, int id, AuthorPayload authorPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.put(baseUrl + Endpoint.AUTHORS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Update author with id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), AuthorPayload.class);
    }

    public Response deleteAuthor(String baseUrl, int id) {
        return RestAssured.delete(baseUrl + Endpoint.AUTHORS.get() + "/" + id);
    }

}

