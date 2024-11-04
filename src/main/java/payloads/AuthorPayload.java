package payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.constants.SerializationProperties;
import lombok.*;

@Getter
@Setter
@Builder
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class AuthorPayload {

    @JsonProperty(SerializationProperties.ID)
    private Integer id;

    @JsonProperty(SerializationProperties.ID_BOOK)
    private Integer idBook;

    @JsonProperty(SerializationProperties.FIRST_NAME)
    private String firstName;

    @JsonProperty(SerializationProperties.LAST_NAME)
    private String lastName;

}
