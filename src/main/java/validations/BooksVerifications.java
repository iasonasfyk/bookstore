package validations;

import common.constants.Constants;
import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import io.restassured.specification.RequestSpecification;
import payloads.BookPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import service.BooksService;

import java.util.ArrayList;
import java.util.List;


public class BooksVerifications {
    protected BooksService booksService = new BooksService();
    protected SoftAssert softAssert = new SoftAssert();

    public BooksVerifications() {
    }

    /****************************     Happy Path Verifications     ****************************/

    public BooksVerifications validateBooksResponseBody(BookPayload responseBody) {
        softAssert.assertNotNull(responseBody.getId(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getPageCount(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getPublishDate(), "value: " + responseBody.getId() + " is null");
        softAssert.assertAll();
        return this;
    }

    public BooksVerifications verifyAllBooks(String url) {
        List<BookPayload> allBooks = booksService.getAllBooks(url);
        allBooks.forEach(u -> {
            softAssert.assertNotNull(u.getId(), "value: " + u.getId() + " is null");
            softAssert.assertNotNull(u.getPageCount(), "value: " + u.getPageCount() + " is null");
            softAssert.assertNotNull(u.getPublishDate(), "value: " + u.getPublishDate() + " is null");
        });
        softAssert.assertAll();
        return this;
    }

    public BooksVerifications verifyBookById(String url, int id) {

        BookPayload responseBody = booksService.getBookById(url, id);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookCreation(String url, BookPayload bookPayload) {

        BookPayload responseBody = booksService.addBook(url, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookUpdate(String url, int id, BookPayload bookPayload) {

        BookPayload responseBody = booksService.updateBook(url, id, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookDeletion(String url, int id) {

        Response response = booksService.deleteBook(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }

    /****************************     Negative Cases Verifications     ****************************/

    public BooksVerifications verifyGetAllBooksError(String url) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    public BooksVerifications verifyGetBookByIdError(String url, int id) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    public BooksVerifications verifyBookCreationError(String url, BookPayload bookPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.post(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    public BooksVerifications verifyBookUpdateError(String url, int id, BookPayload bookPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.put(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    public BooksVerifications verifyBookDeletionError(String url, int id) {
        Response response = booksService.deleteBook(url, id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

}
