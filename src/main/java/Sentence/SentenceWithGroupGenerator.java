package Sentence;

import Entity.DataTheoMa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SentenceWithGroupGenerator implements iSentence {
    private String groupName;
    private ArrayList<DataTheoMa> listData;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public SentenceWithGroupGenerator(String groupName, ArrayList<DataTheoMa> listData){
        df2.setRoundingMode(RoundingMode.DOWN);
        this.groupName = groupName;
        this.listData = listData;
    }

    public String generateSentence() {
        String sentence = "";
        sentence += "Các nhóm cổ phiếu nhóm " + groupName + " có sự biến động với \n";
        var listHanoi =  listData.stream().filter(x -> x.getSan().equals("HNX")).collect(Collectors.toCollection(ArrayList::new));
        if(listHanoi.size() > 0){
            sentence += "Sàn Hà Nội :\n\n";
            HashMap<String, String> listHanoiTheoNgay = new HashMap<>();
            for (DataTheoMa item : listHanoi){
                double chenhlech = Math.abs((item.getOpen() - item.getClose())/item.getOpen()*100);
                if(listHanoiTheoNgay.containsKey(item.getDate())){
                    String quote = ", " + item.getMaCoPhieu() + " ("+df2.format(chenhlech)+"% )";
                    listHanoiTheoNgay.put(item.getDate(), listHanoiTheoNgay.get(item.getDate() + quote));
                }
                else{
                    String quote = "- " + item.getMaCoPhieu() + " ("+df2.format(chenhlech)+"% )";
                    listHanoiTheoNgay.put(item.getDate(), quote);
                }
            }
            for (Map.Entry mapElement : listHanoiTheoNgay.entrySet()) {
                String key = (String)mapElement.getKey();
                String value = ((String)mapElement.getValue());
                sentence = sentence + value + " trong ngay " + key + " \n \n";
            }
        }




        var listSaiGon  = listData.stream().filter(x -> x.getSan().equals("HSX")).collect(Collectors.toCollection(ArrayList::new));
        if(listSaiGon.size() > 0){
            sentence += "Sàn Hồ Chí Minh :\n\n";
            HashMap<String, String> listSaiGonTheoNgay = new HashMap<>();
            for (DataTheoMa item : listSaiGon){
                double chenhlech = Math.abs((item.getOpen() - item.getClose())/item.getOpen()*100);
                if(listSaiGonTheoNgay.containsKey(item.getDate())){
                    String quote = ", " + item.getMaCoPhieu() + " ("+df2.format(chenhlech)+"% )";
                    listSaiGonTheoNgay.put(item.getDate(), listSaiGonTheoNgay.get(item.getDate()) + quote);
                }
                else{
                    String quote = "- " + item.getMaCoPhieu() + " ("+df2.format(chenhlech)+"% )";
                    listSaiGonTheoNgay.put(item.getDate(), quote);
                }
            }
            for (Map.Entry mapElement : listSaiGonTheoNgay.entrySet()) {
                String key = (String)mapElement.getKey();
                String date = key.substring(0,4) +"/"+ key.substring(4,6) + "/" + key.substring(6,8);
                String value = ((String)mapElement.getValue());
                sentence = sentence + value + " trong ngay " + date + " \n \n";
            }
        }

        return sentence;
    }
}
