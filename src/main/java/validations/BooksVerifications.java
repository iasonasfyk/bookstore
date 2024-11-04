package validations;

import configuration.BookConfig;
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

    public BooksVerifications validateBooksResponseBody(BookConfig responseBody) {
        softAssert.assertNotNull(responseBody.getId());
        softAssert.assertNotNull(responseBody.getTitle());
        softAssert.assertNotNull(responseBody.getDescription());
        softAssert.assertNotNull(responseBody.getPageCount());
        softAssert.assertNotNull(responseBody.getExcerpt());
        softAssert.assertNotNull(responseBody.getPublishDate());
        softAssert.assertAll();
        return this;
    }

    public BooksVerifications verifyAllBooks(String url) {
        List<BookConfig> allBooks = booksService.getAllBooks(url);
        allBooks.forEach(u -> {
            softAssert.assertNotNull(u.getId());
            softAssert.assertNotNull(u.getTitle());
            softAssert.assertNotNull(u.getDescription());
            softAssert.assertNotNull(u.getPageCount());
            softAssert.assertNotNull(u.getExcerpt());
            softAssert.assertNotNull(u.getPublishDate());
        });
        softAssert.assertAll();
        return this;
    }

    public BooksVerifications verifyBookById(String url, int id) {

        BookConfig responseBody = booksService.getBookById(url, id);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookCreation(String url, BookConfig bookPayload) {

        BookConfig responseBody = booksService.addBook(url, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookUpdate(String url, int id, BookConfig bookPayload) {

        BookConfig responseBody = booksService.updateBook(url, id, bookPayload);
        validateBooksResponseBody(responseBody);
        return this;
    }

    public BooksVerifications verifyBookDeletion(String url, int id) {

        Response response = booksService.deleteBook(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }


}
