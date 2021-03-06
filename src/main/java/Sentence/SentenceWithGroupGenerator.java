package Sentence;

import Entity.Data;
import Entity.DataRealtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SentenceWithGroupGenerator {
    private String groupName;
    private List<DataRealtime> listData;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public SentenceWithGroupGenerator(String groupName, List<Data> listData){
        df2.setRoundingMode(RoundingMode.DOWN);
        this.groupName = groupName;
        this.listData =  listData.stream().map(data -> (DataRealtime) data).collect(Collectors.toList());
    }

    public String generateSentence() {
        String sentence = "";
        sentence += "Các nhóm cổ phiếu nhóm " + groupName + " có sự biến động với \n";

        if(listData.size() > 0){
            sentence += "Sàn Hồ Chí Minh :\n\n";
            HashMap<String, String> listSaiGonTheoNgay = new HashMap<>();
            for (DataRealtime item : listData){
                double chenhlech = Math.abs((item.getGiaMoCua() - item.getGiaDongCua())/item.getGiaMoCua()*100);
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
                sentence = sentence + value + " trong ngày " + date + " \n \n";
            }
        }

        return sentence;
    }

}
