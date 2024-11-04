import common.commonClasses.DateFormats;
import common.constants.Constants;
import configuration.BookConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import validations.BooksVerifications;

import java.util.Random;

public class BooksTest {

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

    @Test(alwaysRun = true, description = "Verify getBookById", dependsOnMethods = "testStep_1")
    public void testStep_2() {
        booksVerifications.verifyBookById(url, randomInt.nextInt(200));
    }

    @Test(alwaysRun = true, description = "Add book", dependsOnMethods = "testStep_2" )
    public void testStep_3() {

        BookConfig bookPayload = BookConfig.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();

        booksVerifications.verifyBookCreation(url, bookPayload);
    }

    @Test(alwaysRun = true, description = "Update book", dependsOnMethods = "testStep_3" )
    public void testStep_4() {

        BookConfig bookPayload = BookConfig.builder()
                .id(randomInt.nextInt(200))
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();

        booksVerifications.verifyBookUpdate(url, bookPayload.getId(), bookPayload);
    }

    @Test(alwaysRun = true, description = "Delete book", dependsOnMethods = "testStep_4" )
    public void testStep_5() {

        booksVerifications.verifyBookDeletion(url, randomInt.nextInt(200));
    }

}
