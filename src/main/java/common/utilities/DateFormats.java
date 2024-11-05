package common.utilities;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormats {

    protected static DateTimeFormatter payloadFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);

    /**
     * Generate Current Date in "yyyy-MM-dd'T'HH:mm:ss.SSSX" format for payloads usage
     * example: "2018-04-25T14:05:15.953Z"
     */
    public static String getCurrentDateInPayloadFormattedForm() {
        return dateTime.format(payloadFormatter);
    }

    /**
     * Generate Current Date in "yyyy-MM-dd HH:mm:ss" format
     * example: "2016-06-23 09:07:21"
     */
    public static String getCurrentDate() {
        return dateTime.format(formatter);
    }

}
