package Helper;

import Entity.Data;
import Entity.DataRealtime;
import Entity.DataThuCong;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CreateLineChart {
    public static ArrayList<XYChart.Series<Number,Number>> createLines(NumberAxis xAxis, NumberAxis yAxis, List<Data> listData){
        List<DataRealtime> listConcreteData = listData.stream().map(data -> (DataRealtime) data).collect(Collectors.toList());
        ArrayList<XYChart.Series<Number, Number>> listLines = new ArrayList<XYChart.Series<Number, Number>>();

        XYChart.Series<Number, Number> seriesOpen = new XYChart.Series<>();
        seriesOpen.setName("Open");

        XYChart.Series<Number, Number> seriesClose = new XYChart.Series<>();
        seriesClose.setName("Close");

        XYChart.Series<Number, Number> seriesHigh = new XYChart.Series<>();
        seriesHigh.setName("High");

        XYChart.Series<Number, Number> seriesLow = new XYChart.Series<>();
        seriesLow.setName("Low");

        for(DataRealtime item: listConcreteData){
            System.out.println("Date duoc truy vao o dong 34 createlinechart: " + item.getDate());
            seriesOpen.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaMoCua()));
            seriesClose.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaDongCua()));
            seriesHigh.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaCaoNhat()));
            seriesLow.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaThapNhat()));
        }

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(Utilities.convertDateToInt(listConcreteData.get(0).getDate()));
        System.out.println(Utilities.convertDateToInt(listConcreteData.get(0).getDate()));
        xAxis.setUpperBound(Utilities.convertDateToInt(listConcreteData.get(listConcreteData.size()-1).getDate()));
        xAxis.setTickUnit(1);
        xAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number number) {
                long days = number.longValue();
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(dateFormat.parse("19700101"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.add(Calendar.DAY_OF_MONTH, (int) days);

                String tempDate =  dateFormat.format(calendar.getTime());
                return tempDate.substring(0, 4) + "/" + tempDate.substring(4, 6) + "/" + tempDate.substring(6);
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        yAxis.setAutoRanging(false);
        DataRealtime max = Collections.max(listConcreteData, Comparator.comparing(s -> s.getGiaCaoNhat()));
        DataRealtime min = Collections.min(listConcreteData, Comparator.comparing(s -> s.getGiaThapNhat()));
        yAxis.setUpperBound(max.getGiaCaoNhat());
        yAxis.setLowerBound(min.getGiaThapNhat());
        yAxis.setTickUnit(0.1);


        listLines.add(seriesOpen);
        listLines.add(seriesClose);
        listLines.add(seriesHigh);
        listLines.add(seriesLow);

        return listLines;
    }
}
