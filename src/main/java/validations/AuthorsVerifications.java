package validations;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import io.restassured.specification.RequestSpecification;
import payloads.AuthorPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import service.AuthorsService;

import java.util.List;

public class AuthorsVerifications {

    protected AuthorsService authorsService = new AuthorsService();
    protected SoftAssert softAssert = new SoftAssert();

    public AuthorsVerifications() {
    }

    /****************************     Happy Path Verifications     ****************************/

    /**
     * Verify Author payload fields:
     * author id, book id are not null
     * @param responseBody the Author payload
     */
    public AuthorsVerifications validateAuthorsResponseBody(AuthorPayload responseBody) {
        softAssert.assertNotNull(responseBody.getId(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getIdBook(), "value: " + responseBody.getIdBook() + " is null");
        softAssert.assertAll();
        return this;
    }

    /**
     * Verify get all Authors:
     * Get all Authors payloads and verify:
     * response is 200
     * author id, book id fields are not null
     * @param url the base url
     */
    public AuthorsVerifications verifyAllAuthors(String url) {
        List<AuthorPayload> allBooks = authorsService.getAllAuthors(url);
        allBooks.forEach(u -> {
            softAssert.assertNotNull(u.getId(), "value: " + u.getId() + " is null");
            softAssert.assertNotNull(u.getIdBook(), "value: " + u.getIdBook() + " is null");
        });
        softAssert.assertAll();
        return this;
    }

    /**
     * Get Author payload by id and verify:
     * response is 200
     * author id, book id fields are not null
     * @param url the base url
     * @param id the Author id
     */
    public AuthorsVerifications verifyAuthorById(String url, int id) {

        AuthorPayload responseBody = authorsService.getAuthorById(url, id);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    /**
     * Add Author and verify:
     * response is 200
     * author id, book id fields are not null
     * @param url the base url
     * @param authorPayload the Author Payload
     */
    public AuthorsVerifications verifyAuthorCreation(String url, AuthorPayload authorPayload) {

        AuthorPayload responseBody = authorsService.addAuthor(url, authorPayload);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    /**
     * Update Author and verify:
     * response is 200
     * author id, book id fields are not null
     * @param url the base url
     * @param id the Book id
     * @param authorPayload the Author Payload
     */
    public AuthorsVerifications verifyAuthorUpdate(String url, int id, AuthorPayload authorPayload) {

        AuthorPayload responseBody = authorsService.updateAuthor(url, id, authorPayload);
        validateAuthorsResponseBody(responseBody);

        return this;
    }

    /**
     * Delete Author and verify:
     * response is 200
     * @param url the base url
     * @param id the Book id
     */
    public AuthorsVerifications verifyAuthorDeletion(String url, int id) {
        Response response = authorsService.deleteAuthor(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }

    /****************************     Negative Cases Verifications     ****************************/

    /**
     * Verify get all Authors response error 404
     * @param url the wrong url
     */
    public AuthorsVerifications verifyGetAllAuthorsError(String url) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    /**
     * Verify get Author by id response error 404
     * @param url base url
     * @param id the wrong Author id
     */
    public AuthorsVerifications verifyGetAuthorByIdError(String url, int id) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    /**
     * Verify add Author response error 400
     * @param url the base url
     * @param authorPayload the wrong Author Payload
     */
    public AuthorsVerifications verifyAuthorCreationError(String url, AuthorPayload authorPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.post(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    /**
     * Verify update Author response error 404
     * @param url the wrong url
     * @param id the Author id
     * @param authorPayload the Author Payload
     */
    public AuthorsVerifications verifyAuthorUpdateError(String url, int id, AuthorPayload authorPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.put(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    /**
     * Verify delete Author response error 404
     * @param url the wrong url
     */
    public AuthorsVerifications verifyAuthorDeletionError(String url, int id) {
        Response response = authorsService.deleteAuthor(url, id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

}
