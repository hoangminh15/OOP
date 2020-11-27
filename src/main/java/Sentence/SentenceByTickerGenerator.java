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

	