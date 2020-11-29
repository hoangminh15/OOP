package Sentence;

import java.util.*;
import DataAccessor.DatabaseGetter;

import Entity.DataThuCong;
import java.io.*;

public class SentenceBluechip {
	ArrayList<DataThuCong> listBC;
	File file;
	Scanner sc;

	public SentenceBluechip(String date) throws FileNotFoundException {
		File file = new File("blue_chip.txt");
		sc = new Scanner(file);
		listBC = new DatabaseGetter().layDataBluechip(date);
	}

	// Sinh câu về khối lượng giao dịch của các cổ phiếu bluechip trong ngày
	public String highVolume() {
		String message;
		Collections.sort(listBC, new Comparator<DataThuCong>() {
			@Override
			public int compare(DataThuCong o1, DataThuCong o2) {
				return (o1.getVolume() > o2.getVolume()) ? -1 : 1;
			}
		});
		Iterator<DataThuCong> c = listBC.iterator();
		DataThuCong a1 = c.next();
		sc.nextLine();
		message = sc.nextLine() + a1.getMaCoPhieu() + sc.nextLine() + a1.getVolume() + sc.nextLine();
		for (int i = 1; i <= 8; i++) {
			DataThuCong d = c.next();
			message = message + d.getMaCoPhieu() + " với " + d.getVolume() + " cổ phiếu ,";
		}
		DataThuCong d1 = c.next(), d2 = c.next(), d3 = c.next();
		message = message + "\n" + sc.nextLine() + d3.getMaCoPhieu() + " với " + d3.getVolume() + sc.nextLine()
				+ d2.getMaCoPhieu() + " với " + d2.getVolume() + sc.nextLine() + d1.getMaCoPhieu() + " với "
				+ d1.getVolume() + sc.nextLine() + (d1.getVolume() + d2.getVolume() + d3.getVolume()) + sc.nextLine();
		return message;

	}

	// Sinh câu về nhóm cổ phiếu có giá trị có nhất trong ngày
	public String highGiaTri() {
		String message;
		Collections.sort(listBC, new Comparator<DataThuCong>() {
			@Override
			public int compare(DataThuCong o1, DataThuCong o2) {
				return (o1.getClose() > o2.getClose()) ? -1 : 1;
			}
		});
		Iterator<DataThuCong> c = listBC.iterator();
		DataThuCong d1 = c.next(), d2 = c.next(), d3 = c.next();
		sc.nextLine();
		message = sc.nextLine() + d1.getMaCoPhieu() + " với " + d1.getClose() + sc.nextLine() + d2.getMaCoPhieu()
				+ " với " + d2.getVolume() + sc.nextLine() + d3.getMaCoPhieu() + " với " + d3.getVolume()
				+ sc.nextLine();
		return message;
	}

	// Sinh câu về các cổ phiếu có giá trị thấp nhất trong ngày
	public String lowGiaTri() {
		String message;
		Collections.sort(listBC, new Comparator<DataThuCong>() {
			public int compare(DataThuCong o1, DataThuCong o2) {
				return (o1.getClose() < o2.getClose()) ? -1 : 1;
			}
		});
		Iterator<DataThuCong> c = listBC.iterator();
		DataThuCong d1 = c.next(), d2 = c.next(), d3 = c.next();
		sc.nextLine();
		message = sc.nextLine() + d1.getMaCoPhieu() + " với " + d1.getClose() + sc.nextLine() + d2.getMaCoPhieu()
				+ " với " + d2.getClose() + sc.nextLine() + d3.getMaCoPhieu() + " với " + d3.getClose() + sc.nextLine();
		return message;
	}

	// Sinh câu về các cổ phiếu thuộc nhóm bluechip có giá trị giao dịch cao nhất
	// trong ngày
	public String highGTGD() {
		String message;
		Collections.sort(listBC, new Comparator<DataThuCong>() {
			@Override
			public int compare(DataThuCong o1, DataThuCong o2) {
				return (o1.getVolume() * o1.getOpen() > o2.getOpen() * o2.getVolume()) ? -1 : 1;
			}
		});
		Iterator<DataThuCong> c = listBC.iterator();
		DataThuCong d1 = c.next();
		double max = Math.round((d1.getVolume() * d1.getOpen()) / 100000) / 10;
		sc.nextLine();
		message = sc.nextLine() + d1.getMaCoPhieu() + sc.nextLine() + max + sc.nextLine() + c.next().getMaCoPhieu()
				+ ", " + c.next().getMaCoPhieu() + ", " + c.next().getMaCoPhieu() + " và " + c.next().getMaCoPhieu();
		return message;
	}

	// Sinh câu tăng mạnh và giảm mạnh giá trị cổ phiếu trong ngày
	public String tangManh() {
		String message;
		Collections.sort(listBC, new Comparator<DataThuCong>() {
			@Override
			public int compare(DataThuCong o1, DataThuCong o2) {
				return (o1.getClose() - o1.getOpen() > o2.getClose() - o2.getOpen()) ? -1 : 1;

			}
		});
		Iterator<DataThuCong> c = listBC.iterator();
		DataThuCong d1 = c.next(), d2 = c.next();
		sc.nextLine();
		message = sc.nextLine() + d1.getMaCoPhieu() + sc.nextLine() + (d1.getClose() - d1.getOpen()) * 1000
				+ sc.nextLine() + d2.getMaCoPhieu() + sc.nextLine() + (d2.getClose() - d2.getOpen()) * 1000
				+ sc.nextLine();

		return message;
	}

}