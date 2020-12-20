package Entity;

public class DataRealtime extends Data{
    private String maCoPhieu;
    private double giaDongCua;
    private String thayDoi;
    private double giaThamChieu;
    private double giaMoCua;
    private double giaCaoNhat;
    private double giaThapNhat;
    private double klgdKhopLenh;
    private double gtgdKhopLenh;
    private String date;

    public DataRealtime(){

    }

    public DataRealtime(String maCoPhieu, double giaDongCua, String thayDoi, double giaThamChieu, double giaMoCua, double giaCaoNhat, double giaThapNhat, double klgdKhopLenh, double gtgdKhopLenh, String date) {
        this.maCoPhieu = maCoPhieu;
        this.giaDongCua = giaDongCua;
        this.thayDoi = thayDoi;
        this.giaThamChieu = giaThamChieu;
        this.giaMoCua = giaMoCua;
        this.giaCaoNhat = giaCaoNhat;
        this.giaThapNhat = giaThapNhat;
        this.klgdKhopLenh = klgdKhopLenh;
        this.gtgdKhopLenh = gtgdKhopLenh;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaCoPhieu() {
        return maCoPhieu;
    }

    public void setMaCoPhieu(String maCoPhieu) {
        this.maCoPhieu = maCoPhieu;
    }

    public double getGiaDongCua() {
        return giaDongCua;
    }

    public void setGiaDongCua(double giaDongCua) {
        this.giaDongCua = giaDongCua;
    }

    public String getThayDoi() {
        return thayDoi;
    }

    public void setThayDoi(String thayDoi) {
        this.thayDoi = thayDoi;
    }

    public double getGiaThamChieu() {
        return giaThamChieu;
    }

    public void setGiaThamChieu(double giaThamChieu) {
        this.giaThamChieu = giaThamChieu;
    }

    public double getGiaMoCua() {
        return giaMoCua;
    }

    public void setGiaMoCua(double giaMoCua) {
        this.giaMoCua = giaMoCua;
    }

    public double getGiaCaoNhat() {
        return giaCaoNhat;
    }

    public void setGiaCaoNhat(double giaCaoNhat) {
        this.giaCaoNhat = giaCaoNhat;
    }

    public double getGiaThapNhat() {
        return giaThapNhat;
    }

    public void setGiaThapNhat(double giaThapNhat) {
        this.giaThapNhat = giaThapNhat;
    }

    public double getKlgdKhopLenh() {
        return klgdKhopLenh;
    }

    public void setKlgdKhopLenh(double klgdKhopLenh) {
        this.klgdKhopLenh = klgdKhopLenh;
    }

    public double getGtgdKhopLenh() {
        return gtgdKhopLenh;
    }

    public void setGtgdKhopLenh(double gtgdKhopLenh) {
        this.gtgdKhopLenh = gtgdKhopLenh;
    }
}
