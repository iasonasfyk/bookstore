package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import configuration.BookConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class BooksService {

    public List<BookConfig> getAllBooks(String baseUrl) {

        List<BookConfig> booksList = new ArrayList<>();

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.BOOKS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get all books failed with status code: " + response.getStatusCode());
        }
        for (Object object : response.jsonPath().getList("")) {
            booksList.add(UtilitiesClass.deserialize(object, BookConfig.class));
        }
        return booksList;
    }

    public BookConfig getBookById(String baseUrl, int id) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get book by id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookConfig.class);
    }

    public BookConfig addBook(String baseUrl, BookConfig bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.post(baseUrl + Endpoint.BOOKS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Add book with payload = " + bookPayload.toString() + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookConfig.class);
    }

    public BookConfig updateBook(String baseUrl, int id, BookConfig bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.put(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Update book with id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookConfig.class);
    }

    public Response deleteBook(String baseUrl, int id) {
        return RestAssured.delete(baseUrl + Endpoint.BOOKS.get() + "/" + id);
    }

}

