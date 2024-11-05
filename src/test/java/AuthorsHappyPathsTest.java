import common.utilities.DateFormats;
import common.constants.Constants;
import payloads.AuthorPayload;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import validations.AuthorsVerifications;

import java.util.Random;

public class AuthorsHappyPathsTest {

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

    @Test(alwaysRun = true, description = "Verify get All Authors response")
    public void testStep_1() {
        authorsVerifications.verifyAllAuthors(url);
    }

    @Test(alwaysRun = true, description = "Verify get Author By Id response", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        authorsVerifications.verifyAuthorById(url, randomInt.nextInt(622));
    }

    @Test(alwaysRun = true, description = "Verify Author creation", dependsOnMethods = "testStep_2" )
    public void testStep_3() {

        AuthorPayload authorPayload = AuthorPayload.builder()
                .id(623)
                .idBook(randomInt.nextInt(200))
                .firstName(null)
                .lastName(lastName)
                .build();

        authorsVerifications.verifyAuthorCreation(url, authorPayload);
    }

    @Test(alwaysRun = true, description = "Verify Author update", dependsOnMethods = "testStep_3" )
    public void testStep_4() {

        AuthorPayload authorPayload = AuthorPayload.builder()
                .id(randomInt.nextInt(622))
                .idBook(randomInt.nextInt(200))
                .firstName(firstName)
                .lastName(null)
                .build();

        authorsVerifications.verifyAuthorUpdate(url, authorPayload.getId(), authorPayload);
    }

    @Test(alwaysRun = true, description = "Verify Author deletion", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        authorsVerifications.verifyAuthorDeletion(url, randomInt.nextInt(622));
    }

}
