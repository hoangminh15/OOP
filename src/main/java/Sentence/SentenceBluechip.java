package Sentence;

import java.util.*;
import DAO.DatabaseGetter;

import Entity.DataTheoMa;

public class SentenceBluechip {
	ArrayList<DataTheoMa> listBC;

	public SentenceBluechip(String date) {
		listBC = new DatabaseGetter().layDataBluechip(date);
	}

	// Sinh câu về khối lượng giao dịch của các cổ phiếu bluechip trong ngày
	public String highVolume() {
		String message;
		Collections.sort(listBC, new Comparator<DataTheoMa>() {
			@Override
			public int compare(DataTheoMa o1, DataTheoMa o2) {
				return (o1.getVolume() > o2.getVolume()) ? -1 : 1;
			}
		});
		Iterator<DataTheoMa> c = listBC.iterator();
		DataTheoMa a1 = c.next();
		message = "Khối lượng giao dịch hôm nay tăng mạnh, dẫn đầu các cổ phiếu trong nhóm bluechip là "
				+ a1.getMaCoPhieu() + " với " + a1.getVolume() + " cổ phiếu , tiếp theo sau là ";
		for (int i = 1; i <= 8; i++) {
			DataTheoMa d = c.next();
			message = message + d.getMaCoPhieu() + " với " + d.getVolume() + " cổ phiếu ,";
		}
		DataTheoMa d1 = c.next(), d2 = c.next(), d3 = c.next();
		message = message
				+ "\nTrong khi đó, 3 cổ phiếu trong nhóm bluechip có khối lượng giao dịch thấp nhất là cổ phiếu "
				+ d3.getMaCoPhieu() + " với " + d3.getVolume() + " cổ phiếu, tiếp theo sau là " + d2.getMaCoPhieu()
				+ " với " + d2.getVolume() + " cổ phiếu " + d1.getMaCoPhieu() + " với " + d1.getVolume()
				+ " cổ phiếu. Cả ba cổ phiếu này chỉ có " + (d1.getVolume() + d2.getVolume() + d3.getVolume())
				+ " cổ phiếu được chuyển nhượng, chiếm tỉ lệ rất nhỏ trên thị trường";
		return message;

	}

	// Sinh câu về nhóm cổ phiếu có giá trị có nhất trong ngày
	public String highGiaTri() {
		String message;
		Collections.sort(listBC, new Comparator<DataTheoMa>() {
			@Override
			public int compare(DataTheoMa o1, DataTheoMa o2) {
				return (o1.getClose() > o2.getClose()) ? -1 : 1;
			}
		});
		Iterator<DataTheoMa> c = listBC.iterator();
		DataTheoMa d1 = c.next(), d2 = c.next(), d3 = c.next();
		message = "\nKết thúc phiên giao dịch ngày hôm nay, Top 3 cổ phiếu trong nhóm bluechip có giá trị cổ phiếu cao nhất là "
				+ d1.getMaCoPhieu() + " với " + d1.getClose() + " nghìn đồng, tiếp theo sau là " + d2.getMaCoPhieu()
				+ " với " + d2.getVolume() + " nghìn đồng " + d3.getMaCoPhieu() + " với " + d3.getVolume()
				+ " nghìn đồng";
		return message;
	}

	// Sinh câu về các cổ phiếu có giá trị thấp nhất trong ngày
	public String lowGiaTri() {
		String message;
		Collections.sort(listBC, new Comparator<DataTheoMa>() {
			public int compare(DataTheoMa o1, DataTheoMa o2) {
				return (o1.getClose() < o2.getClose()) ? -1 : 1;
			}
		});
		Iterator<DataTheoMa> c = listBC.iterator();
		DataTheoMa d1 = c.next(), d2 = c.next(), d3 = c.next();
		message = "\nTrong khi đó kết thúc phiên giao dịch ngày hôm nay lại chứng kiến sự giảm sút giá trị cổ phiếu của "
				+ d1.getMaCoPhieu() + " với " + d1.getClose() + " nghìn đồng, " + d2.getMaCoPhieu() + " với "
				+ d2.getClose() + " nghìn đồng và" + d3.getMaCoPhieu() + " với " + d3.getClose() + " nghìn đồng.";
		return message;
	}

	// Sinh câu về các cổ phiếu thuộc nhóm bluechip có giá trị giao dịch cao nhất
	// trong ngày
	public String highGTGD() {
		String message;
		Collections.sort(listBC, new Comparator<DataTheoMa>() {
			@Override
			public int compare(DataTheoMa o1, DataTheoMa o2) {
				return (o1.getVolume() * o1.getOpen() > o2.getOpen() * o2.getVolume()) ? -1 : 1;
			}
		});
		Iterator<DataTheoMa> c = listBC.iterator();
		DataTheoMa d1 = c.next();
		double max = Math.round((d1.getVolume() * d1.getOpen()) / 100000) / 10;
		message = "\nDẫn đầu nhóm cổ phiếu bluechip về giá trị giao dịch trong ngày hôm nay là cổ phiếu "
				+ d1.getMaCoPhieu() + " với giá trị gần " + max + " tỷ đồng, tiếp theo là các mã "
				+ c.next().getMaCoPhieu() + ", " + c.next().getMaCoPhieu() + ", " + c.next().getMaCoPhieu() + " và "
				+ c.next().getMaCoPhieu();
		return message;
	}

	// Sinh câu tăng mạnh và giảm mạnh giá trị cổ phiếu trong ngày
	public String tangManh() {
		String message;
		Collections.sort(listBC, new Comparator<DataTheoMa>() {
			@Override
			public int compare(DataTheoMa o1, DataTheoMa o2) {
				return (o1.getClose() - o1.getOpen() > o2.getClose() - o2.getOpen()) ? -1 : 1;

			}
		});
		Iterator<DataTheoMa> c = listBC.iterator();
		DataTheoMa d1 = c.next(), d2 = c.next();
		message = "\nSàn chứng khoán hôm nay chứng kiến sự tăng mạnh của nhóm cổ phiếu bluechip, đặc biệt là cổ phiếu "
				+ d1.getMaCoPhieu() + "với việc tăng " + (d1.getClose() - d1.getOpen()) * 1000 + " đồng và cổ phiếu "
				+ d2.getMaCoPhieu() + " tăng " + (d2.getClose() - d2.getOpen()) * 1000 + " đồng";

		return message;
	}

}