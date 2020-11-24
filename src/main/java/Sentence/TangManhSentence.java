package Sentence;

import Entity.DataTheoMa;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;

public class TangManhSentence implements iSentence {
    DataTheoMa data;

    public TangManhSentence(ArrayList<DataTheoMa> data){
        this.data = data;
    }

    public String generateSentence(){

        Double open = data.getOpen();
        Double close = data.getClose();
        Double percentageChange = abs((close - open)/open);
        Double roundedPercentageChange = Math.round(percentageChange * 1000.0) / 1000.0;
        String maCoPhieu = data.getMaCoPhieu();
        String sentence = "";
        if (data.getMaCoPhieu() == "HNX" ) sentence += "Sàn Hà Nội ngày "+ data.getDate() +": \n\n";
                else sentence +=" Sàn Hồ Chí Minh ngày "+ data.getDate()+": \n\n";
        sentence += "Cổ phiểu " + maCoPhieu + " tăng mạnh " + roundedPercentageChange + " %, từ  " + open + " lên " + close + "\n";
        return sentence;
    }


}
