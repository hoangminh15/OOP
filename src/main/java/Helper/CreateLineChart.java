package Helper;

import Entity.DataTheoMa;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CreateLineChart {
    public static ArrayList<XYChart.Series<Number,Number>> createLines(NumberAxis xAxis, NumberAxis yAxis, ArrayList<DataTheoMa> listData){
        ArrayList<XYChart.Series<Number, Number>> listLines = new ArrayList<XYChart.Series<Number, Number>>();

        XYChart.Series<Number, Number> seriesOpen = new XYChart.Series<>();
        seriesOpen.setName("Open");

        XYChart.Series<Number, Number> seriesClose = new XYChart.Series<>();
        seriesClose.setName("Close");

        XYChart.Series<Number, Number> seriesHigh = new XYChart.Series<>();
        seriesHigh.setName("High");

        XYChart.Series<Number, Number> seriesLow = new XYChart.Series<>();
        seriesLow.setName("Low");

        Collections.sort(listData, new Comparator<DataTheoMa>() {
            @Override
            public int compare(DataTheoMa o1, DataTheoMa o2) {
                return Integer.parseInt(o1.getDate()) <  Integer.parseInt(o2.getDate()) ? 1 : -1;
            }
        });

        for(DataTheoMa item: listData){
            seriesOpen.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getOpen()));
            seriesClose.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getClose()));
            seriesHigh.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getHigh()));
            seriesLow.getData().add(new XYChart.Data<>(Utilities.convertDateToInt(item.getDate()), item.getLow()));
        }

        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(Utilities.convertDateToInt(listData.get(0).getDate()));
        xAxis.setLowerBound(Utilities.convertDateToInt(listData.get(listData.size()-1).getDate()));
        xAxis.setTickUnit(1);
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                int nam =  object.intValue() / 365;
                int thang = (object.intValue() - nam * 365)/32;
                int ngay = object.intValue() - nam * 365 - thang * 32;
                return String.valueOf(nam) + "/" + String.valueOf(thang)+ "/" + String.valueOf(ngay);
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        yAxis.setAutoRanging(false);
        DataTheoMa max = Collections.max(listData, Comparator.comparing(s -> s.getHigh()));
        DataTheoMa min = Collections.min(listData, Comparator.comparing(s -> s.getLow()));
        yAxis.setUpperBound(max.getHigh());
        yAxis.setLowerBound(min.getLow());
        yAxis.setTickUnit(0.1);


        listLines.add(seriesOpen);
        listLines.add(seriesClose);
        listLines.add(seriesHigh);
        listLines.add(seriesLow);

        return listLines;
    }
}
