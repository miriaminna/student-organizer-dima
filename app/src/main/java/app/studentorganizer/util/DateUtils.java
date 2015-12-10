package app.studentorganizer.util;

import org.joda.time.LocalDate;

/**
 * Created by henko on 10.12.15.
 */
public class DateUtils {
    public static String dateToString(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + '-' + monthOfYear + '-' + dayOfMonth;
    }
    public static String localDateToString(LocalDate localDate) {
        return dateToString(localDate.getYear(),
                localDate.getMonthOfYear(),
                localDate.getDayOfMonth());
    }
}
