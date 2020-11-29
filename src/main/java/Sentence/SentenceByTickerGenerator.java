package Sentence;

import Entity.DataThuCong;
import static java.lang.StrictMath.abs;
import java.io.*;
import java.util.Scanner;
import DataAccessor.DatabaseGetter;
import Helper.YesterdayFinder;

public class SentenceByTickerGenerator {
	DataThuCong data;
	File file;
	Scanner sc;

	public SentenceByTickerGenerator(DataThuCong data) throws FileNotFoundException {
		file = new File("cau_theo_ngay.txt");
		sc = new Scanner(file);
		this.data = data;
	}

	public String generateSentence() {
		Double open = data.getOpen();
		Double close = data.getClose();
		Double percentageChange = abs((close - open) / open);
		Double roundedPercentageChange = Math.round(percentageChange * 1000.0) / 1000.0;
		String maCoPhieu = data.getMaCoPhieu();
		String loaiThayDoi;
		if (close > open) {
			loaiThayDoi = " tăng ";
		} else if (close == open) {
			loaiThayDoi = " không đổi ";
		} else
			loaiThayDoi = " giảm ";
		sc.nextLine();
		String sentence = sc.nextLine() + maCoPhieu + " " + loaiThayDoi + " " + +roundedPercentageChange
				+ sc.nextLine();
		return sentence;
	}

	public String getReferencePrice() {
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + data.getClose() + sc.nextLine();
		return message;
	}

	public String giaCP() {
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + +data.getOpen() + sc.nextLine()
				+ data.getMaCoPhieu() + sc.nextLine() + data.getLow() + sc.nextLine() + data.getHigh() + sc.nextLine()
				+ data.getClose() + sc.nextLine();
		return message;
	}

	public String khoiLuongGD() {
		double giatriGD = data.getOpen() * data.getVolume();
		String giatri = new String();
		if (giatriGD > Math.pow(10, 6)) {
			giatriGD = Math.round(giatriGD / Math.pow(10, 5)) / 10;
			giatri = giatriGD + " tỷ đồng";
		} else {
			giatriGD = Math.round(giatriGD / Math.pow(10, 2)) / 10;
			giatri = giatriGD + " triệu đồng";
		}
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + data.getVolume() + sc.nextLine()
				+ giatri;
		return message;
	}

	public String GiaTranSan(String dateData, String masan, String macophieu) {

		String yesterday = new YesterdayFinder().Sau(dateData);
		DataThuCong dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getClose();
		double tran = Math.round(referencePrice * 1.07);
		double san = Math.round(referencePrice * 0.93);
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + tran + sc.nextLine() + san
				+ sc.nextLine();
		return message;
	}

	public String giaCoPhieu(String dateData, String masan, String macophieu) {
		String yesterday = new YesterdayFinder().Sau(dateData);
		DataThuCong dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getClose();
		double close = data.getClose();
		double percentageChange = abs((close - referencePrice) / referencePrice);
		double tyle = Math.round((percentageChange * 1000)) / 10;
		String soSanh;
		String type = (close > referencePrice) ? " tang" : " giam";
		sc.nextLine();
		soSanh = sc.nextLine() + data.getMaCoPhieu() + type + " " + Math.abs((referencePrice - close)) * 1000 + sc.nextLine()
				+ tyle + sc.nextLine();

		return soSanh;
	}

	public String soSanhGD(String dateData, String masan, String macophieu) {
		String message;
		String yesterday = new YesterdayFinder().Sau(dateData);
		DataThuCong dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double yesterdayKL = dataYesterday.getVolume();
		double ss = Math.abs(data.getVolume() - yesterdayKL);
		String type = (data.getVolume() > yesterdayKL) ? " tăng " : " giảm ";
		sc.nextLine();
		message = sc.nextLine() + macophieu + type + ss + sc.nextLine();
		return message;
	}
}

