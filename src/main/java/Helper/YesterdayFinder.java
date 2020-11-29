package Helper;

public class YesterdayFinder {

	public int findDayOfWeek(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6));
		int m = (month - 3 + 4800) % 4800;
		int y = (year + m / 12) % 400;
		m %= 12;
		return (y + y / 4 - y / 100 + (13 * m + 2) / 5 + day + 2) % 7;
	}

	//Format date ve dang ex: 20201123
	public String formatDate(int ngay, int thang, int nam) {
		String day, month, year;
		if (ngay < 10) {
			day = "0" + ngay;
		} else
			day = Integer.toString(ngay);
		if (thang < 10) {
			month = "0" + thang;
		} else
			month = Integer.toString(thang);
		year = Integer.toString(nam);
		String yesterday1 = year + month + day;
		return yesterday1;
	}

	public String findYesterday(String date) {
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
		return formatDate(ngay, thang, nam);
	}

	public String Sau(String date) {
		String yesterday;
		do {
			yesterday = findYesterday(date);
		} while (findDayOfWeek(date) == 0 || findDayOfWeek(date) == 6);
		return yesterday;
	}
	
}