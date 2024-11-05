import common.utilities.RandomGenerator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import BusinessObjects.BooksBO;
import payloads.BookPayload;
import validations.BooksVerifications;

public class BooksHappyPathsTest {

    private final BooksVerifications booksVerifications = new BooksVerifications();
    private final BooksBO booksBO = new BooksBO();
    private String url;


    @BeforeSuite(alwaysRun = true)
    @Parameters({"baseUrl"})
    public void preConditions(String baseUrl) {
        url = baseUrl;
    }

    @Test(alwaysRun = true, description = "Verify getAllBooks response")
    public void testStep_1() {
        booksVerifications.verifyAllBooks(url);
    }

    @Test(alwaysRun = true, description = "Verify get Book By Id response", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        booksVerifications.verifyBookById(url, RandomGenerator.generateRandomIntegerGivenTopBound(200));
    }

    @Test(alwaysRun = true, description = "Verify Book creation", dependsOnMethods = "testStep_2" )
    public void testStep_3() {
        BookPayload bookPayload = booksBO.buildDefaultBookPayload();
        booksVerifications.verifyBookCreation(url, bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book update", dependsOnMethods = "testStep_3" )
    public void testStep_4() {
        BookPayload bookPayload = booksBO.buildBookPayloadForBookUpdate();
        booksVerifications.verifyBookUpdate(url, bookPayload.getId(), bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book deletion", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        booksVerifications.verifyBookDeletion(url, RandomGenerator.generateRandomIntegerGivenTopBound(200));
    }

}
