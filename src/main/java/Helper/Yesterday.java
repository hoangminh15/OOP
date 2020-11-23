package Helper;

public class Yesterday {

	public static int findDayOfWeek(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		int m = (month - 3 + 4800) % 4800;
		int y = (year + m / 12) % 400;
		m %= 12;
		return (y + y / 4 - y / 100 + (13 * m + 2) / 5 + day + 2) % 7;
	}

	public static String date(int ngay, int thang, int nam) {
		String day, month, year;
		if (ngay < 10) {
			day = "0" + Integer.toString(ngay);
		} else
			day = Integer.toString(ngay);
		if (thang < 10) {
			month = "0" + Integer.toString(thang);
		} else
			month = Integer.toString(thang);
		year = Integer.toString(nam);
		String yesterday1 = year + month + day;
		return yesterday1;
	}

	public static String yesterday(String date) {
		int dayFeb = 0, nam, thang, ngay;
		nam = Integer.parseInt(date.substring(0, 4));
		thang = Integer.parseInt(date.substring(4, 6));
		ngay = Integer.parseInt(date.substring(6));
		if (nam % 4 == 0)
			dayFeb = 29;
		else
			dayFeb = 28;
		int ngayMonth[] = { 0, 31, dayFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (ngay == 1 && thang == 1) {
			nam -= 1;
			thang = 12;
			ngay = 31;

		} else {
			if (ngay == 1) {
				thang -= 1;
				ngay = ngayMonth[thang];
			} else {
				ngay -= 1;
			}
		}
		return date(ngay, thang, nam);
	}

	public static String Sau(String date) {
		String Date = date;
		do {
			Date = Yesterday.yesterday(Date);
		} while (findDayOfWeek(Date) == 0 || findDayOfWeek(Date) == 6);
		return Date;
	}
	
}