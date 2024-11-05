import common.constants.Constants;
import common.utilities.DateFormats;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import payloads.AuthorPayload;
import validations.AuthorsVerifications;

import java.util.Random;

public class AuthorsNegativeCasesTest {

    private final AuthorsVerifications authorsVerifications = new AuthorsVerifications();
    private final Random randomInt = new Random();
    private String url;
    private final String firstName = Constants.FIRST_NAME_PREFIX + DateFormats.getCurrentDate();
    private final String lastName = Constants.LAST_NAME_PREFIX + DateFormats.getCurrentDate();


    @BeforeSuite(alwaysRun = true)
    @Parameters({"baseUrl"})
    public void preConditions(String baseUrl) {
        url = baseUrl;
    }

    @Test(alwaysRun = true, description = "Verify get All Authors response error with wrong url")
    public void testStep_1() {
        authorsVerifications.verifyGetAllAuthorsError(url + "/1");
    }

    @Test(alwaysRun = true, description = "Verify get Author By Id response error with wrong author id", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        authorsVerifications.verifyGetAuthorByIdError(url, -1);
    }

    @Test(alwaysRun = true, description = "Verify Author creation error with null author id", dependsOnMethods = "testStep_2" )
    public void testStep_3() {

        AuthorPayload authorPayload = AuthorPayload.builder()
                .id(null)
                .idBook(randomInt.nextInt(200))
                .firstName(firstName)
                .lastName(lastName)
                .build();

        authorsVerifications.verifyAuthorCreationError(url, authorPayload);
    }

    @Test(alwaysRun = true, description = "Verify Author update error with wrong url", dependsOnMethods = "testStep_3" )
    public void testStep_4() {

        AuthorPayload authorPayload = AuthorPayload.builder()
                .id(randomInt.nextInt(622))
                .firstName(firstName)
                .lastName(lastName)
                .build();

        authorsVerifications.verifyAuthorUpdateError(url + "*", authorPayload.getId(), authorPayload);
    }

    @Test(alwaysRun = true, description = "Verify Author deletion error with wrong url", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        authorsVerifications.verifyAuthorDeletionError(url + "/1", randomInt.nextInt(622));
    }

}
