import BusinessObjects.AuthorsBO;
import common.utilities.RandomGenerator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import payloads.AuthorPayload;
import validations.AuthorsVerifications;

public class AuthorsNegativeCasesTest {

    private final AuthorsVerifications authorsVerifications = new AuthorsVerifications();
    private final AuthorsBO authorsBO = new AuthorsBO();
    private String url;


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
        AuthorPayload authorsPayloadWithNullAuthorId = authorsBO.buildAuthorsPayloadWithNullAuthorId();
        authorsVerifications.verifyAuthorCreationError(url, authorsPayloadWithNullAuthorId);
    }

    @Test(alwaysRun = true, description = "Verify Author update error with wrong url", dependsOnMethods = "testStep_3" )
    public void testStep_4() {
        AuthorPayload defaultAuthorsPayload = authorsBO.buildDefaultAuthorsPayload();
        authorsVerifications.verifyAuthorUpdateError(url + "*", defaultAuthorsPayload.getId(), defaultAuthorsPayload);
    }

    @Test(alwaysRun = true, description = "Verify Author deletion error with wrong url", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        authorsVerifications.verifyAuthorDeletionError(url + "/1", RandomGenerator.generateRandomIntegerGivenTopBound(622));
    }

}
