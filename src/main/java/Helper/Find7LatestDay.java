package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        Collections.reverse(recentDateList);

        //Filter out Saturday and Sunday
        recentDateList = recentDateList.stream().filter(dateToBeFiltered -> {
            int ngay = Integer.parseInt(dateToBeFiltered.substring(0, 2));
            int thang = Integer.parseInt(dateToBeFiltered.substring(2, 4));
            int nam = Integer.parseInt(dateToBeFiltered.substring(4));
            int dayOfWeek = new DateValidator().findDayOfWeek(ngay, thang, nam);
            if(dayOfWeek == 0 || dayOfWeek == 6) return false;
            else return true;
        }).collect(Collectors.toList());

        return recentDateList;
    }
}
