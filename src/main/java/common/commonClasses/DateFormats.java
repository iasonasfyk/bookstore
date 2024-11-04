package common.commonClasses;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormats {

    protected static DateTimeFormatter payloadFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);

    public static String getCurrentDateInPayloadFormattedForm() {
        return dateTime.format(payloadFormatter);
    }

    public static String getCurrentDate() {
        return dateTime.format(formatter);
    }

}
