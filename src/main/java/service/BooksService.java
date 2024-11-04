package service;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import payloads.BookPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class BooksService {

    public List<BookPayload> getAllBooks(String baseUrl) {

        List<BookPayload> booksList = new ArrayList<>();

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.BOOKS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get all books failed with status code: " + response.getStatusCode());
        }
        for (Object object : response.jsonPath().getList("")) {
            booksList.add(UtilitiesClass.deserialize(object, BookPayload.class));
        }
        return booksList;
    }

    public BookPayload getBookById(String baseUrl, int id) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get book by id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    public BookPayload addBook(String baseUrl, BookPayload bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.post(baseUrl + Endpoint.BOOKS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Add book with payload = " + bookPayload.toString() + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    public BookPayload updateBook(String baseUrl, int id, BookPayload bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.put(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Update book with id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    public Response deleteBook(String baseUrl, int id) {
        return RestAssured.delete(baseUrl + Endpoint.BOOKS.get() + "/" + id);
    }

}

