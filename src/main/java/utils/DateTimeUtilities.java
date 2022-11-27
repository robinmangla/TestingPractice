package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtilities {
    /**
     * @param format
     * @return currentdate in the given format
     *
     *         This Function Will take the given fromat as input and return the
     *         System date in given fomat
     */
    public static String currentSystemDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

}
