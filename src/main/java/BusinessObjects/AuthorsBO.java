package BusinessObjects;

import common.constants.Constants;
import common.utilities.DateFormats;
import common.utilities.RandomGenerator;
import payloads.AuthorPayload;

public class AuthorsBO {
    private final String firstName = Constants.FIRST_NAME_PREFIX + DateFormats.getCurrentDate();
    private final String lastName = Constants.LAST_NAME_PREFIX + DateFormats.getCurrentDate();

    public AuthorPayload buildAuthorPayloadWithNullFirstName() {
        return AuthorPayload.builder()
                .id(623)
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(null)
                .lastName(lastName)
                .build();
    }

    public AuthorPayload buildAuthorPayloadWithNullLastName() {
        return AuthorPayload.builder()
                .id(RandomGenerator.generateRandomIntegerGivenTopBound(622))
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(firstName)
                .lastName(null)
                .build();
    }

    public AuthorPayload buildAuthorsPayloadWithNullAuthorId() {
        return AuthorPayload.builder()
                .id(null)
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public AuthorPayload buildDefaultAuthorsPayload() {
        return AuthorPayload.builder()
                .id(RandomGenerator.generateRandomIntegerGivenTopBound(622))
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }



}
