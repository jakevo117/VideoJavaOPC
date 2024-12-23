package Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static String formatDateTime(LocalDateTime dateTimeNeedFormat){
        DateTimeFormatter myFormatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return myFormatDateTime.format(dateTimeNeedFormat);
    }

    public static String formatDate(LocalDate dateNeedFormat) {
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return myFormatDate.format(dateNeedFormat);
    }
}
