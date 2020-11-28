package Sentence;

import Entity.DataTheoMa;
import Helper.Yesterday;
import static java.lang.StrictMath.abs;

import DAO.DatabaseGetter;

public class SentenceByTickerGenerator implements iSentence {
	DataTheoMa data;

	public SentenceByTickerGenerator(DataTheoMa data) {
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
		String sentence = "Trong phiên giao dịch ngày hôm nay, cổ phiểu " + maCoPhieu + " " +loaiThayDoi+" "+
				+ roundedPercentageChange + "%, và có khả năng tiếp tục tăng trong tương lai";
		return sentence;
	}

	public String getReferencePrice() {

		String message = "Kết thúc phiên giao dịch hôm nay cổ phiếu " + data.getMaCoPhieu() + " đứng giá tham chiếu "
				+ data.getClose() + " nghìn đồng";
		return message;
	}

	public String giaCP() {
		String message = "Mở đầu phiên giao dịch hôm nay, cổ phiếu " + data.getMaCoPhieu() + "được bán với mức giá"
				+ data.getOpen() + "nghìn đồng. Trong phiên giao dịch cổ phiếu " + data.getMaCoPhieu()
				+ "có mức dao động trong khoảng " + data.getLow() + " đến " + data.getHigh()
				+ " nghìn đồng, nhưng đến cuối ngày chốt lại con số " + data.getClose() + " nghìn đồng";
		return message;
	}

	public String GiaTranSan(String dateData, String masan, String macophieu) {

		String yesterday = Yesterday.Sau(dateData);
		DataTheoMa dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getClose();
		double tran = Math.round(referencePrice * 1.07);
		double san = Math.round(referencePrice * 0.93);
		String message = "Trong phiên giao dịch hôm nay, cổ phiếu " + data.getMaCoPhieu()
				+ " có giá trần của cổ phiếu là: " + tran + " nghìn đồng, và giá sàn của cổ phiếu là: " + san
				+ " nghìn đồng";
		return message;
	}

	public String giaCoPhieu(String dateData, String masan, String macophieu) {
		String yesterday = Yesterday.Sau(dateData);
		DataTheoMa dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double referencePrice = dataYesterday.getClose();
		double close = data.getClose();
		double percentageChange = abs((close - referencePrice) / referencePrice);
		double tyle = Math.round((percentageChange * 1000)) / 10;
		String soSanh;
		if (close < referencePrice) {
			soSanh = "So với ngày hôm qua, cổ phiếu " + data.getMaCoPhieu() + " giảm " + (referencePrice - close) * 1000
					+ " đồng (" + tyle + "%)";
		} else {
			if (close > referencePrice) {
				soSanh = "So với ngày hôm qua, cổ phiếu " + data.getMaCoPhieu() + " tăng " + (close - referencePrice)
						+ " đồng (" + tyle + "%)";
			} else {
				soSanh = "So với ngày hôm qua, cổ phiếu vẫn giữ giá " + data.getClose() + " đồng";
			}
		}
		return soSanh;
	}

	public String khoiLuongGD() {
		String message = "Kết thúc phiên giao dịch ngày hôm nay cổ phiếu " + data.getMaCoPhieu()
				+ " đạt khối lượng giao dịch là : " + data.getVolume() + " cổ phiếu";
		double giatriGD = data.getOpen() * data.getVolume();
		if (giatriGD > Math.pow(10, 6)) {
			giatriGD = Math.round(giatriGD / Math.pow(10, 5)) / 10;
			message = message + " tương đương gần " + giatriGD + " tỷ đồng";
		} else {
			giatriGD = Math.round(giatriGD / Math.pow(10, 2)) / 10;
			message = message + " tương đương gần " + giatriGD + " triệu đồng";
		}

		return message;
	}

	public String soSanhGD(String dateData, String masan, String macophieu) {
		String message;
		String yesterday = Yesterday.Sau(dateData);
		DataTheoMa dataYesterday = new DatabaseGetter().layDataTheoMa(yesterday, masan, macophieu);
		double yesterdayKL = dataYesterday.getVolume();
		double ss = Math.abs(data.getVolume() - yesterdayKL);
		if (data.getVolume() > yesterdayKL) {
			message = "So với ngày hôm qua khối lượng giao dịch cổ phiếu " + macophieu + " tăng " + ss + " cổ phiếu";
		} else {
			if (data.getVolume() < yesterdayKL) {
				message = "So với ngày hôm qua khối lượng giao dịch giảm " + ss + " cổ phiếu";
			} else {
				message = "So với ngày hôm qua khối lượng giao dịch cổ phiếu không tăng";
			}
		}
		return message;
	}

}