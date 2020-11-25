package DAO;

import Entity.DataRealtime;
import Entity.DataTheoMa;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Logger;

public class RealtimeCrawler extends DataGetter {
    public static void main(String[] args) {
        DataRealtime dataRealtime = new DataRealtime();
        final String url = "https://s.cafef.vn/TraCuuLichSu2/1/HOSE/12/11/2020.chn";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table#table2sort tr");
            for (Element row: rows) {
                if (row.select("td.Item_DateItem_lsg").text().equals("")) {
                    continue;
                } else {
                    String ticker = row.select("td.Item_DateItem_lsg").text();
                    dataRealtime.setTicker(ticker);
                    String[] price = row.select("td.Item_Price1").text().split("\\s+");
                    for (int i = 0; i <= 9; i++){
                        dataRealtime.setClose(Double.parseDouble(price[0]));
                        dataRealtime.setHigh(Double.parseDouble(price[3]));
                        dataRealtime.setLow(Double.parseDouble(price[4]));
                        dataRealtime.setOpen(Double.parseDouble(price[2]));
                        dataRealtime.setVolume(Double.parseDouble(price[5]));

                    }
                    String change = row.select("td.Item_ChangePrice_lsg").text();




                    //System.out.println(ticker+" "+price[0]+" "+price[2]+" "+price[3]+" "+price[4]+" ");
                    //int number = Interger.parseInt("34")
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public DataRealtime layDataRealtime(String ticker, float close , float high, float low, float open, float volume) {
        //Query theo ngay - san - ma
        String sql = "INSERT INTO StockData.datarealtime." + ticker + "." + close + "." + high + "." + low + " + open +  " + volume + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            setFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tra ve object chua data
        return new DataRealtime(ticker, close, high, low, open, volume);
    }
}