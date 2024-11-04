package service;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import logger.Log;
import payloads.BookPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class BooksService {

    /**
     * Calls GET {baseUrl}/api/v1/Books
     * Get all Books
     * @param baseUrl the base url
     * @return list of all the deserialized Book payloads
     */
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
        Log.info("Successful Get all Books with response: \n" + response.getBody().asString());
        return booksList;
    }

    /**
     * Calls GET {baseUrl}/api/v1/Books/{id}
     * Get specific Book by id
     * @param baseUrl the base url
     * @param id the Book id
     * @return the deserialized Book payload
     */
    public BookPayload getBookById(String baseUrl, int id) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Get book by id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful GET Book By Id with response: \n" + response.getBody().asString());
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    /**
     * Calls POST {baseUrl}/api/v1/Books
     * Add Book
     * @param baseUrl the base url
     * @param bookPayload the Book payload
     * @return the deserialized Book payload
     */
    public BookPayload addBook(String baseUrl, BookPayload bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.post(baseUrl + Endpoint.BOOKS.get());

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Add book with payload = " + bookPayload.toString() + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful POST Book with response: \n" + response.getBody().asString());
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    /**
     * Calls PUT {baseUrl}/api/v1/Books/{id}
     * Update Book
     * @param baseUrl the base url
     * @param id the Book id
     * @param bookPayload the Book payload
     * @return the deserialized Book payload
     */
    public BookPayload updateBook(String baseUrl, int id, BookPayload bookPayload) {

        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.put(baseUrl + Endpoint.BOOKS.get() + "/" + id);

        if (!UtilitiesClass.isHttpResponseStatusCodeExpected(response)) {
            throw new AssertionError("Update book with id = " + id + ",  failed with status code: " + response.getStatusCode());
        }
        Log.info("Successful PUT Book with response: \n" + response.getBody().asString());
        return UtilitiesClass.deserialize(response.jsonPath().get(), BookPayload.class);
    }

    /**
     * Calls DELETE {baseUrl}/api/v1/Books/{id}
     * Delete Book
     * @param baseUrl the base url
     * @param id the Book id
     * @return the response
     */
    public Response deleteBook(String baseUrl, int id) {
        return RestAssured.delete(baseUrl + Endpoint.BOOKS.get() + "/" + id);
    }

}

