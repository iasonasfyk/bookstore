package BusinessObjects;

import common.constants.Constants;
import common.utilities.DateFormats;
import java.util.Random;
import payloads.BookPayload;

public class BooksBO {
    protected Random randomInt  = new Random();;

    public BookPayload buildDefaultBookPayload() {
        return BookPayload.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();
    }

    public BookPayload buildBookPayloadForBookUpdate() {
        return BookPayload.builder()
                .id(randomInt.nextInt(200))
                .title(null)
                .description(null)
                .pageCount(randomInt.nextInt(1000))
                .excerpt(null)
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();
    }

    public BookPayload buildBookPayloadWithNullPageCount() {
        return BookPayload.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(null)
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();
    }

    public BookPayload buildBookPayloadWithWrongDateFormat() {
        return BookPayload.builder()
                .id(randomInt.nextInt(200))
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(randomInt.nextInt(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDate())
                .build();
    }


}
