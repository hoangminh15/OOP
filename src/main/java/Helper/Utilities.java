package Helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilities {
    public static int convertDateToInt(String date){
        int value = 0;

        value += Integer.parseInt(date.substring(0,4)) * 365 ;
        value += Integer.parseInt(date.substring(4,6)) * 32;
        value += Integer.parseInt(date.substring(6,8));

        return value;

    }
}
