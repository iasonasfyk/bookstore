package validations;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import io.restassured.specification.RequestSpecification;
import payloads.BookPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import service.BooksService;

import java.util.List;


public class BooksVerifications {
    protected BooksService booksService = new BooksService();
    protected SoftAssert softAssert = new SoftAssert();

    public BooksVerifications() {
    }

    /****************************     Happy Path Verifications     ****************************/

    /**
     * Verify Book payload fields:
     * book id, page count and publish date are not null
     * @param responseBody the Book payload
     */
    public BooksVerifications validateBooksResponseBody(BookPayload responseBody) {
        softAssert.assertNotNull(responseBody.getId(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getPageCount(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getPublishDate(), "value: " + responseBody.getId() + " is null");
        softAssert.assertAll();
        return this;
    }

    /**
     * Verify get all Books
     * Get all Books payloads and verify:
     * response is 200
     * book id, page count and publish date fields are not null
     * @param url the base url
     */
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

    /**
     * Get Book by id and verify:
     * response is 200
     * book id, page count and publish date fields are not null
     * @param url the base url
     * @param id the Book id
     */
    public BooksVerifications verifyBookById(String url, int id) {

        BookPayload responseBody = booksService.getBookById(url, id);
        validateBooksResponseBody(responseBody);
        return this;
    }

    /**
     * Add Book and verify:
     * response is 200
     * book id, page count and publish date fields are not null
     * @param url the base url
     * @param bookPayload the Book Payload
     */
    public BooksVerifications verifyBookCreation(String url, BookPayload bookPayload) {

        BookPayload responseBody = booksService.addBook(url, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    /**
     * Update Book and verify:
     * response is 200
     * book id, page count and publish date fields are not null
     * @param url the base url
     * @param id the Book id
     * @param bookPayload the Book Payload
     */
    public BooksVerifications verifyBookUpdate(String url, int id, BookPayload bookPayload) {

        BookPayload responseBody = booksService.updateBook(url, id, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    /**
     * Delete Book and verify:
     * response is 200
     * @param url the base url
     * @param id the Book id
     */
    public BooksVerifications verifyBookDeletion(String url, int id) {

        Response response = booksService.deleteBook(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }

    /****************************     Negative Cases Verifications     ****************************/

    /**
     * Verify get all Books response error 404
     * @param url the wrong url
     */
    public BooksVerifications verifyGetAllBooksError(String url) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    /**
     * Verify get Book by id response error 404
     * @param url base url
     * @param id the wrong Book id
     */
    public BooksVerifications verifyGetBookByIdError(String url, int id) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    /**
     * Verify add Book response error 400
     * @param url the base url
     * @param bookPayload the wrong Book Payload
     */
    public BooksVerifications verifyBookCreationError(String url, BookPayload bookPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.post(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    /**
     * Verify update Book response error 400
     * @param url the base url
     * @param id the Book id
     * @param bookPayload the wrong Book Payload
     */
    public BooksVerifications verifyBookUpdateError(String url, int id, BookPayload bookPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(bookPayload);
        Response response = request.put(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    /**
     * Verify delete Book response error 404
     * @param url the wrong url
     */
    public BooksVerifications verifyBookDeletionError(String url, int id) {
        Response response = booksService.deleteBook(url, id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

}
