package configuration;

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
public class BookConfig {

    @JsonProperty(SerializationProperties.ID)
    private Integer id;

    @JsonProperty(SerializationProperties.TITLE)
    private String title;

    @JsonProperty(SerializationProperties.DESCRIPTION)
    private String description;

    @JsonProperty(SerializationProperties.PAGE_COUNT)
    private Integer pageCount;

    @JsonProperty(SerializationProperties.EXCERPT)
    private String excerpt;

    @JsonProperty(SerializationProperties.PUBLISH_DATE)
    private String publishDate;

}
