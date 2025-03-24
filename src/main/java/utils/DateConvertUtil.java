package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtil {
    /**
     * convert string to date
     * @param dateStr 2025-02-02 15:00:00
     * @return Date
     */
    public static Date StringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
