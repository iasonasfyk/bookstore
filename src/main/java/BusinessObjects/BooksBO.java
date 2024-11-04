package BusinessObjects;

import common.constants.Constants;
import common.utilities.DateFormats;
import common.utilities.RandomGenerator;
import payloads.BookPayload;

public class BooksBO {

    /**
     * Build Book Payload with default values in all fields
     */
    public BookPayload buildDefaultBookPayload() {
        return BookPayload.builder()
                .id(201)
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(RandomGenerator.generateRandomIntGivenTopBound(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();
    }

    /**
     * Build Book Payload with null title, description and excerpt
     */
    public BookPayload buildBookPayloadWithNullTitleDescriptionExcerpt() {
        return BookPayload.builder()
                .id(RandomGenerator.generateRandomIntGivenTopBound(200))
                .title(null)
                .description(null)
                .pageCount(RandomGenerator.generateRandomIntGivenTopBound(1000))
                .excerpt(null)
                .publishDate(DateFormats.getCurrentDateInPayloadFormattedForm())
                .build();
    }

    /**
     * Build Book Payload with null pageCount
     */
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

    /**
     * Build Book Payload with wrong date format
     */
    public BookPayload buildBookPayloadWithWrongDateFormat() {
        return BookPayload.builder()
                .id(RandomGenerator.generateRandomIntGivenTopBound(200))
                .title(Constants.BOOK_PREFIX + DateFormats.getCurrentDate())
                .description(Constants.DESCRIPTION_PREFIX + DateFormats.getCurrentDate())
                .pageCount(RandomGenerator.generateRandomIntGivenTopBound(1000))
                .excerpt(Constants.EXCERPT_PREFIX + DateFormats.getCurrentDate())
                .publishDate(DateFormats.getCurrentDate())
                .build();
    }


}
