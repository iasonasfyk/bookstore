import common.constants.Constants;
import common.utilities.DateFormats;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import payloads.BookPayload;
import validations.BooksVerifications;

import java.util.Random;

public class BooksNegativeCasesTest {

    private final BooksVerifications booksVerifications = new BooksVerifications();
    protected Random randomInt = new Random();
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

        BookPayload bookPayload = BookPayload.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(null)
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();

        booksVerifications.verifyBookCreationError(url, bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book update error with wrong date format", dependsOnMethods = "testStep_3" )
    public void testStep_4() {

        BookPayload bookPayload = BookPayload.builder()
                .id(randomInt.nextInt(200))
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDate())
                .build();

        booksVerifications.verifyBookUpdateError(url, bookPayload.getId(), bookPayload);
    }

    @Test(alwaysRun = true, description = "Verify Book deletion error with wrong url", dependsOnMethods = "testStep_4" )
    public void testStep_5() {
        booksVerifications.verifyBookDeletionError(url + "/0", randomInt.nextInt(200));
    }

}
