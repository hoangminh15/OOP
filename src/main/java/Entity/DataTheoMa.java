package Entity;

public class DataTheoMa {

    private String maCoPhieu;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private String san;

    public DataTheoMa(){

    }

    public DataTheoMa(String maCoPhieu, String date, double open, double high, double low, double close, double volume) {
        this.maCoPhieu = maCoPhieu;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getMaCoPhieu() {
        return maCoPhieu;
    }

    public void setMaCoPhieu(String maCoPhieu) {
        this.maCoPhieu = maCoPhieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }
}
