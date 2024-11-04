import common.utilities.DateFormats;
import common.constants.Constants;
import payloads.BookPayload;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import validations.BooksVerifications;

import java.util.Random;

public class BooksHappyPathsTest {

    private final BooksVerifications booksVerifications = new BooksVerifications();
    protected Random randomInt = new Random();
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
        booksVerifications.verifyBookById(url, randomInt.nextInt(200));
    }

    @Test(alwaysRun = true, description = "Verify Book creation", dependsOnMethods = "testStep_2" )
    public void testStep_3() {

        BookPayload bookPayload = BookPayload.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();

        booksVerifications.verifyBookCreation(url, bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book update", dependsOnMethods = "testStep_3" )
    public void testStep_4() {

        BookPayload bookPayload = BookPayload.builder()
                .id(randomInt.nextInt(200))
                .title(null)
                .description(null)
                .pageCount(randomInt.nextInt(1000))
                .excerpt(null)
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();

        booksVerifications.verifyBookUpdate(url, bookPayload.getId(), bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book deletion", dependsOnMethods = "testStep_4" )
    public void testStep_5() {

        booksVerifications.verifyBookDeletion(url, randomInt.nextInt(200));
    }

}
