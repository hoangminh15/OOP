package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utilities {
    public static long convertDateToInt(String date){
        //Date ex: 20201220
        long result = 0;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date ourDate = dateFormat.parse(date);
            long dif = ourDate.getTime();
            result = TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
