package BusinessObjects;

import common.constants.Constants;
import common.utilities.DateFormats;
import common.utilities.RandomGenerator;
import payloads.AuthorPayload;

public class AuthorsBO {
    private final String firstName = Constants.FIRST_NAME_PREFIX + DateFormats.getCurrentDate();
    private final String lastName = Constants.LAST_NAME_PREFIX + DateFormats.getCurrentDate();

    /**
     * Build Author Payload with null First Name
     */
    public AuthorPayload buildAuthorPayloadWithNullFirstName() {
        return AuthorPayload.builder()
                .id(623)
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(null)
                .lastName(lastName)
                .build();
    }

    /**
     * Build Author Payload with null Last Name
     */
    public AuthorPayload buildAuthorPayloadWithNullLastName() {
        return AuthorPayload.builder()
                .id(RandomGenerator.generateRandomIntegerGivenTopBound(622))
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(firstName)
                .lastName(null)
                .build();
    }

    /**
     * Build Author Payload with null Author id
     */
    public AuthorPayload buildAuthorsPayloadWithNullAuthorId() {
        return AuthorPayload.builder()
                .id(null)
                .idBook(RandomGenerator.generateRandomIntegerGivenTopBound(200))
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    /**
     * Build Author Payload with default values in all fields
     */
    public AuthorPayload buildDefaultAuthorsPayload() {
        return AuthorPayload.builder()
                .id(RandomGenerator.generateRandomIntegerGivenTopBound(622))
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }



}
