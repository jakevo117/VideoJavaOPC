package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static String formatDateTime(LocalDateTime dateTimeNeedFormat){
        DateTimeFormatter myFormatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return myFormatDateTime.format(dateTimeNeedFormat);
    }
}
