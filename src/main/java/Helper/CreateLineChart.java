package Helper;

import Entity.Data;
import Entity.DataRealtime;
import Entity.DataThuCong;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
            System.out.println(item.getDate());
            seriesOpen.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaMoCua()));
            seriesClose.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaDongCua()));
            seriesHigh.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaCaoNhat()));
            seriesLow.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getGiaThapNhat()));
        }

        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(Utilities.convertDateToInt(listConcreteData.get(0).getDate()));
        System.out.println(listConcreteData.get(0).getDate());
        xAxis.setLowerBound(Utilities.convertDateToInt(listConcreteData.get(listConcreteData.size()-1).getDate()));
        xAxis.setTickUnit(1);
        xAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                int nam = object.intValue() / 365;
                int thang = (object.intValue() - nam * 365) / 32;
                int ngay = object.intValue() - nam * 365 - thang * 32;
                return ngay + "/" + thang + "/" + nam;
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
