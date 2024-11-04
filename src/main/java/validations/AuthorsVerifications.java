package validations;

import configuration.AuthorConfig;
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

    public AuthorsVerifications validateAuthorsResponseBody(AuthorConfig responseBody) {
        softAssert.assertNotNull(responseBody.getId());
        softAssert.assertNotNull(responseBody.getIdBook());
        softAssert.assertNotNull(responseBody.getFirstName());
        softAssert.assertNotNull(responseBody.getLastName());
        softAssert.assertAll();
        return this;
    }

    public AuthorsVerifications verifyAllAuthors(String url) {
        List<AuthorConfig> allBooks = authorsService.getAllAuthors(url);
        allBooks.forEach(u -> {
            softAssert.assertNotNull(u.getId());
            softAssert.assertNotNull(u.getIdBook());
            softAssert.assertNotNull(u.getFirstName());
            softAssert.assertNotNull(u.getLastName());
        });
        softAssert.assertAll();
        return this;
    }

    public AuthorsVerifications verifyAuthorById(String url, int id) {

        AuthorConfig responseBody = authorsService.getAuthorById(url, id);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    public AuthorsVerifications verifyAuthorCreation(String url, AuthorConfig authorPayload) {

        AuthorConfig responseBody = authorsService.addAuthor(url, authorPayload);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    public AuthorsVerifications verifyAuthorUpdate(String url, int id, AuthorConfig authorPayload) {

        AuthorConfig responseBody = authorsService.updateAuthor(url, id, authorPayload);
        validateAuthorsResponseBody(responseBody);
        return this;
    }

    public AuthorsVerifications verifyAuthorDeletion(String url, int id) {
        Response response = authorsService.deleteAuthor(url, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Response status code is not correct. Status code:  " + response.getStatusCode());
        return this;
    }

}
