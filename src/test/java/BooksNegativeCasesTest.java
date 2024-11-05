import BusinessObjects.BooksBO;
import common.utilities.RandomGenerator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import payloads.BookPayload;
import validations.BooksVerifications;

public class BooksNegativeCasesTest {

    private final BooksVerifications booksVerifications = new BooksVerifications();
    private final BooksBO booksBO = new BooksBO();
    private String url;


    @BeforeSuite(alwaysRun = true)
    @Parameters({"baseUrl"})
    public void preConditions(String baseUrl) {
        url = baseUrl;
    }

    @Test(alwaysRun = true, description = "Verify get All Books response error with wrong url")
    public void testStep_1() {
        booksVerifications.verifyGetAllBooksError(url + "s");
    }

    @Test(alwaysRun = true, description = "Verify get Book By Id response error with wrong book id", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        booksVerifications.verifyGetBookByIdError(url, 0);
    }

    @Test(alwaysRun = true, description = "Verify Book creation error with null page count", dependsOnMethods = "testStep_2" )
    public void testStep_3() {
        BookPayload bookPayloadWithNullPageCount = booksBO.buildBookPayloadWithNullPageCount();
        booksVerifications.verifyBookCreationError(url, bookPayloadWithNullPageCount);
    }

    @Test(alwaysRun = true, description = "Verify Book update error with wrong date format", dependsOnMethods = "testStep_3" )
    public void testStep_4() {
        BookPayload bookPayloadWithWrongDateFormat = booksBO.buildBookPayloadWithWrongDateFormat();
        booksVerifications.verifyBookUpdateError(url, bookPayloadWithWrongDateFormat.getId(), bookPayloadWithWrongDateFormat);
    }

    @Test(alwaysRun = true, description = "Verify Book deletion error with wrong url", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        booksVerifications.verifyBookDeletionError(url + "/0", RandomGenerator.generateRandomIntegerGivenTopBound(200));
    }

}
