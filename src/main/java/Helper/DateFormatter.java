package Helper;

public class DateFormatter {
    public static String formatDate(String date){
        String nam = date.substring(0, 4);
        String thang = date.substring(4, 6);
        String ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }
        if (Integer.valueOf(thang) < 10) {
            thang = "0" + thang;
        }
        return nam + thang + ngay;
    }
}
