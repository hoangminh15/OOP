package Sentence;

import java.util.*;

import Entity.Data;
import Entity.DataRealtime;
import Helper.FormNumber;
import Entity.DataRealtime;

import java.io.*;
import java.util.stream.Collectors;

public class SentenceBluechip {
    List<DataRealtime> listBC;
    File file;
    Scanner sc;
    private FormNumber form;

    public SentenceBluechip(List<Data> bluechipList) throws FileNotFoundException {
        File file = new File("blue_chip.txt");
        sc = new Scanner(file);
        listBC = bluechipList.stream().map(bluechip -> (DataRealtime) bluechip).collect(Collectors.toList());
        form = new FormNumber();
    }

    // Sinh câu về khối lượng giao dịch của các cổ phiếu bluechip trong ngày
    public String highVolume() {
        String message;
        Collections.sort(listBC, (Comparator<DataRealtime>) (o1, o2) -> {
            return (o1.getKlgdKhopLenh() > o2.getKlgdKhopLenh()) ? -1 : 1;
        });
        Iterator<DataRealtime> c = listBC.iterator();
        DataRealtime a1 = c.next();
        sc.nextLine();
        message = sc.nextLine()
                + a1.getMaCoPhieu()
                + sc.nextLine()
                + form.getForm(a1.getKlgdKhopLenh()) +
                sc.nextLine();
        for (int i = 1; i <= 8; i++) {
            DataRealtime d = c.next();
            message = message + d.getMaCoPhieu() + " với " + form.getForm(d.getKlgdKhopLenh()) + " cổ phiếu ,";
        }
        DataRealtime d1 = c.next(), d2 = c.next(), d3 = c.next();
        message = message + "\n" + sc.nextLine() + d3.getMaCoPhieu() + " với " + form.getForm(d3.getKlgdKhopLenh())
                + sc.nextLine() + d2.getMaCoPhieu() + " với " + form.getForm(d2.getKlgdKhopLenh()) + sc.nextLine()
                + d1.getMaCoPhieu() + " với " + form.getForm(d1.getKlgdKhopLenh()) + sc.nextLine()
                + form.getForm((d1.getKlgdKhopLenh() + d2.getKlgdKhopLenh() + d3.getKlgdKhopLenh())) + sc.nextLine();
        return message;

    }

    // Sinh câu về nhóm cổ phiếu có giá trị có nhất trong ngày
    public String highGiaTri() {
        String message;
        Collections.sort(listBC, (o1, o2) -> (o1.getGiaDongCua() > o2.getGiaDongCua()) ? -1 : 1);
        Iterator<DataRealtime> c = listBC.iterator();
        DataRealtime d1 = c.next(), d2 = c.next(), d3 = c.next();
        sc.nextLine();
        message = sc.nextLine() + d1.getMaCoPhieu() + " với " + form.getForm(d1.getGiaDongCua()) + sc.nextLine()
                + d2.getMaCoPhieu() + " với " + form.getForm(d2.getGiaDongCua()) + sc.nextLine() + d3.getMaCoPhieu()
                + " với " + form.getForm(d3.getGiaDongCua()) + sc.nextLine();
        return message;
    }

    // Sinh câu về các cổ phiếu có giá trị thấp nhất trong ngày
    public String lowGiaTri() {
        String message;
        Collections.sort(listBC, (o1, o2) -> (o1.getGiaDongCua() < o2.getGiaDongCua()) ? -1 : 1);
        Iterator<DataRealtime> c = listBC.iterator();
        DataRealtime d1 = c.next(), d2 = c.next(), d3 = c.next();
        sc.nextLine();
        message = sc.nextLine() + d1.getMaCoPhieu() + " với " + form.getForm(d1.getGiaDongCua()) + sc.nextLine()
                + d2.getMaCoPhieu() + " với " + form.getForm(d2.getGiaDongCua()) + sc.nextLine() + d3.getMaCoPhieu()
                + " với " + form.getForm(d3.getGiaDongCua()) + sc.nextLine();
        return message;
    }

    // Sinh câu về các cổ phiếu thuộc nhóm bluechip có giá trị giao dịch cao nhất
    // trong ngày
    public String highGTGD() {
        String message;
        Collections.sort(listBC, new Comparator<DataRealtime>() {
            @Override
            public int compare(DataRealtime o1, DataRealtime o2) {
                return (o1.getKlgdKhopLenh() * o1.getGiaMoCua() > o2.getGiaMoCua() * o2.getKlgdKhopLenh()) ? -1 : 1;
            }
        });
        Iterator<DataRealtime> c = listBC.iterator();
        DataRealtime d1 = c.next();
        double max = Math.round((d1.getKlgdKhopLenh() * d1.getGiaMoCua()) / 100000) / 10;
        sc.nextLine();
        message = sc.nextLine() + d1.getMaCoPhieu() + sc.nextLine() + form.getForm(max) + sc.nextLine()
                + c.next().getMaCoPhieu() + ", " + c.next().getMaCoPhieu() + ", " + c.next().getMaCoPhieu() + " và "
                + c.next().getMaCoPhieu();
        return message;
    }

    // Sinh câu tăng mạnh và giảm mạnh giá trị cổ phiếu trong ngày
    public String tangManh() {
        String message;
        Collections.sort(listBC, new Comparator<DataRealtime>() {
            @Override
            public int compare(DataRealtime o1, DataRealtime o2) {
                return (o1.getGiaDongCua() - o1.getGiaMoCua() > o2.getGiaDongCua() - o2.getGiaMoCua()) ? -1 : 1;

            }
        });
        Iterator<DataRealtime> c = listBC.iterator();
        DataRealtime d1 = c.next(), d2 = c.next();
        sc.nextLine();
        message = sc.nextLine() + d1.getMaCoPhieu() + sc.nextLine()
                + form.getForm((d1.getGiaDongCua() - d1.getGiaMoCua()) * 1000) + sc.nextLine() + d2.getMaCoPhieu()
                + sc.nextLine() + form.getForm((d2.getGiaDongCua() - d2.getGiaMoCua()) * 1000) + sc.nextLine();

        return message;
    }

}