package Sentence;

import Entity.DataTheoMa;

import static java.lang.StrictMath.abs;

public class SentenceByTickerGenerator implements iSentence{
    DataTheoMa data;

    public SentenceByTickerGenerator(DataTheoMa data){
        this.data = data;
    }

    public String generateSentence(){
        //Can sinh câu phức tạp hơn thế này
        Double open = data.getOpen();
        Double close = data.getClose();
        Double percentageChange = abs((close - open)/open);
        Double roundedPercentageChange = Math.round(percentageChange * 1000.0) / 1000.0;
        String maCoPhieu = data.getMaCoPhieu();
        String loaiThayDoi;
        if (close > open){
            loaiThayDoi = " tăng ";
        } else if (close == open){
            loaiThayDoi = " không đổi ";
        } else loaiThayDoi = " giảm ";
        String sentence = "Cổ phiểu " + maCoPhieu + " hôm nay " + loaiThayDoi + " " + roundedPercentageChange + "%, và có khả năng tiếp tục tăng trong tương lai";
        return sentence;
    }


}
