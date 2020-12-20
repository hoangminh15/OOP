package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Find7LatestDay {
    public List<String> lay7NgayGanNhat(String selectedDay){
        //Selected day dang 02112020
        List<String> recentDateList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = null;
        try {
            date = dateFormat.parse(selectedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        recentDateList.add(dateFormat.format(date));
        cal.setTime(date);
        for (int i = 0; i < 6; i++){
            cal.add(Calendar.DATE, -1);
            recentDateList.add(new SimpleDateFormat("ddMMyyyy").format(cal.getTime()));
        }
        return recentDateList;
    }
}
