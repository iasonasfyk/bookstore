package validations;

import common.enums.Endpoint;
import common.utilities.UtilitiesClass;
import io.restassured.specification.RequestSpecification;
import payloads.AuthorPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import payloads.BookPayload;
import service.AuthorsService;

import java.util.List;

public class AuthorsVerifications {

    protected AuthorsService authorsService = new AuthorsService();
    protected SoftAssert softAssert = new SoftAssert();

    public AuthorsVerifications() {
    }

    /****************************     Happy Path Verifications     ****************************/

    public AuthorsVerifications validateAuthorsResponseBody(AuthorPayload responseBody) {
        softAssert.assertNotNull(responseBody.getId(), "value: " + responseBody.getId() + " is null");
        softAssert.assertNotNull(responseBody.getIdBook(), "value: " + responseBody.getIdBook() + " is null");
        softAssert.assertNotNull(responseBody.getFirstName(), "value: " + responseBody.getFirstName() + " is null");
        softAssert.assertNotNull(responseBody.getLastName(), "value: " + responseBody.getLastName() + " is null");
        softAssert.assertAll();
        return this;
    }

    public AuthorsVerifications verifyAllAuthors(String url) {
        List<AuthorPayload> allBooks = authorsService.getAllAuthors(url);
        allBooks.forEach(u -> {
            softAssert.assertNotNull(u.getId(), "value: " + u.getId() + " is null");
            softAssert.assertNotNull(u.getIdBook(), "value: " + u.getIdBook() + " is null");
            softAssert.assertNotNull(u.getFirstName(), "value: " + u.getFirstName() + " is null");
            softAssert.assertNotNull(u.getLastName(), "value: " + u.getLastName() + " is null");
        });
        softAssert.assertAll();
        return this;
    }

    public AuthorsVerifications verifyAuthorById(String url, int id) {

        AuthorPayload responseBody = authorsService.getAuthorById(url, id);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    public AuthorsVerifications verifyAuthorCreation(String url, AuthorPayload authorPayload) {

        AuthorPayload responseBody = authorsService.addAuthor(url, authorPayload);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    public AuthorsVerifications verifyAuthorUpdate(String url, int id, AuthorPayload authorPayload) {

        AuthorPayload responseBody = authorsService.updateAuthor(url, id, authorPayload);
        validateAuthorsResponseBody(responseBody);

        return this;
    }

    public AuthorsVerifications verifyAuthorDeletion(String url, int id) {
        Response response = authorsService.deleteAuthor(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }

    /****************************     Negative Cases Verifications     ****************************/

    public AuthorsVerifications verifyGetAllAuthorsError(String url) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    public AuthorsVerifications verifyGetAuthorByIdError(String url, int id) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader();
        Response response = request.get(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    public AuthorsVerifications verifyAuthorCreationError(String url, AuthorPayload authorPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.post(url + Endpoint.BOOKS.get());
        Assert.assertEquals(response.getStatusCode(), 400);
        return this;
    }

    public AuthorsVerifications verifyAuthorUpdateError(String url, int id, AuthorPayload authorPayload) {
        RequestSpecification request =  UtilitiesClass.getRequestWithHeader().body(authorPayload);
        Response response = request.put(url + Endpoint.BOOKS.get() + "/" + id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

    public AuthorsVerifications verifyAuthorDeletionError(String url, int id) {
        Response response = authorsService.deleteAuthor(url, id);
        Assert.assertEquals(response.getStatusCode(), 404);
        return this;
    }

}
