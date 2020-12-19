package Sentence;

import DataAccessor.DataGetter;
import DataService.DataTheoMaRealtime;
import Entity.Data;
import Entity.DataRealtime;

import static java.lang.StrictMath.abs;
import Helper.FormNumber;
import java.io.*;
import java.util.Scanner;


import DataAccessor.RealtimeDataGetter;
import Helper.YesterdayFinder;

public class SentenceByTickerGenerator {
	DataRealtime data;
	File file;
	Scanner sc;
	private FormNumber form = new FormNumber();

	public SentenceByTickerGenerator(DataRealtime data) throws FileNotFoundException {
		File file = new File("cau_theo_ngay.txt");
		sc = new Scanner(file);
		this.data = data;
	}

	public String generateSentence() {
		Double open = data.getGiaMoCua();
		Double close = data.getGiaDongCua();
		Double percentageChange = abs((close - open) / open);
		Double roundedPercentageChange = Math.round(percentageChange * 1000.0) / 1000.0;
		String maCoPhieu = data.getMaCoPhieu();
		String loaiThayDoi;
		if (close > open) {
			loaiThayDoi = "tăng";
		} else if (close == open) {
			loaiThayDoi = "không đổi";
		} else
			loaiThayDoi = "giảm";
		sc.nextLine();
		String sentence = sc.nextLine() + maCoPhieu + " " + loaiThayDoi + " " + +roundedPercentageChange
				+ sc.nextLine();
		return sentence;
	}

	public String getReferencePrice() {
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + form.getForm(data.getGiaDongCua())
				+ sc.nextLine();
		return message;
	}

	public String giaCP() {
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + form.getForm(data.getGiaMoCua())
				+ sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + form.getForm(data.getGiaThapNhat())
				+ sc.nextLine() + form.getForm(data.getGiaCaoNhat()) + sc.nextLine() + form.getForm(data.getGiaDongCua())
				+ sc.nextLine();
		return message;
	}

	public String khoiLuongGD() {
		double giatriGD = data.getGtgdKhopLenh();
		String giatri = new String();
		if (giatriGD > Math.pow(10, 6)) {
			giatriGD = Math.round(giatriGD / Math.pow(10, 5)) / 10;
			giatri = form.getForm(giatriGD) + " tỷ đồng";
		} else {
			giatriGD = Math.round(giatriGD / Math.pow(10, 2)) / 10;
			giatri = form.getForm(giatriGD) + " triệu đồng";
		}
		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() + data.getKlgdKhopLenh() + sc.nextLine()
				+ giatri;
		return message;
	}


	public String GiaTranSan(String dateData, String masan, String macophieu) {

		String yesterday = new YesterdayFinder().Sau(dateData);
		DataGetter dataGetter = new DataGetter();
		dataGetter.setDataTheoMaFetcher(new DataTheoMaRealtime());
		DataRealtime dataYesterday = (DataRealtime) dataGetter.thucHienLayDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getGiaDongCua();
		double tran = Math.round(referencePrice * 1.07);
		double san = Math.round(referencePrice * 0.93);

		sc.nextLine();
		String message = sc.nextLine() + data.getMaCoPhieu() + sc.nextLine() +form.getForm(tran) + sc.nextLine() + form.getForm(san)
				+ sc.nextLine();
		return message;
	}

	public String giaCoPhieu(String dateData, String masan, String macophieu) {
		String yesterday = new YesterdayFinder().Sau(dateData);
		DataRealtime dataYesterday = new RealtimeDataGetter().layDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getGiaDongCua();
		double close = data.getGiaDongCua();
		double percentageChange = abs((close - referencePrice) / referencePrice);
		double tyle = Math.round((percentageChange * 1000)) / 10;
		String soSanh;
		String type = (close > referencePrice) ? " tăng" : " giảm";
		sc.nextLine();
		soSanh = sc.nextLine() + data.getMaCoPhieu() + type + " " + form.getForm(Math.abs((referencePrice - close)) * 1000)
				+ sc.nextLine() + form.getForm(tyle) + sc.nextLine();

		return soSanh;
	}

	public String soSanhGD(String dateData, String masan, String macophieu) {
		String message;
		String yesterday = new YesterdayFinder().Sau(dateData);
		DataRealtime dataYesterday = new RealtimeDataGetter().layDataTheoMa(yesterday, masan, macophieu);
		double yesterdayKL = dataYesterday.getKlgdKhopLenh();
		String ss = form.getForm(Math.abs(data.getKlgdKhopLenh() - yesterdayKL));
		String type = (data.getKlgdKhopLenh() > yesterdayKL) ? " tăng " : " giảm ";
		sc.nextLine();
		message = sc.nextLine() + macophieu + type + ss + sc.nextLine();
		return message;
	}
}
