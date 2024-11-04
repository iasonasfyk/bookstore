import BusinessObjects.AuthorsBO;
import common.utilities.RandomGenerator;
import payloads.AuthorPayload;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import validations.AuthorsVerifications;

public class AuthorsHappyPathsTest {

    private final AuthorsVerifications authorsVerifications = new AuthorsVerifications();
    private final AuthorsBO authorsBO = new AuthorsBO();
    private String url;

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
        authorsVerifications.verifyAuthorById(url, RandomGenerator.generateRandomIntGivenTopBound(622));
    }

    @Test(alwaysRun = true, description = "Verify Author creation", dependsOnMethods = "testStep_2" )
    public void testStep_3() {
        AuthorPayload authorPayloadWithNullFirstName = authorsBO.buildAuthorPayloadWithNullFirstName();
        authorsVerifications.verifyAuthorCreation(url, authorPayloadWithNullFirstName);
    }

    @Test(alwaysRun = true, description = "Verify Author update", dependsOnMethods = "testStep_3" )
    public void testStep_4() {
        AuthorPayload authorPayloadWithNullLastName = authorsBO.buildAuthorPayloadWithNullLastName();
        authorsVerifications.verifyAuthorUpdate(url, authorPayloadWithNullLastName.getId(), authorPayloadWithNullLastName);
    }

    @Test(alwaysRun = true, description = "Verify Author deletion", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        authorsVerifications.verifyAuthorDeletion(url, RandomGenerator.generateRandomIntGivenTopBound(622));
    }

}
