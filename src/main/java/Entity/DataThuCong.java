package Entity;

public class DataThuCong extends Data{

    private String maCoPhieu;
    private String date;
    private double giaMoCua;
    private double giaCaoNhat;
    private double giaThapNhat;
    private double giaDongCua;
    private double klgdKhopLenh;

    public DataThuCong(){

    }

    public DataThuCong(String maCoPhieu, String date, double giaMoCua, double giaCaoNhat, double giaThapNhat, double giaDongCua, double klgdKhopLenh) {
        this.maCoPhieu = maCoPhieu;
        this.date = date;
        this.giaMoCua = giaMoCua;
        this.giaCaoNhat = giaCaoNhat;
        this.giaThapNhat = giaThapNhat;
        this.giaDongCua = giaDongCua;
        this.klgdKhopLenh = klgdKhopLenh;
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

    public double getGiaDongCua() {
        return giaDongCua;
    }

    public void setGiaDongCua(double giaDongCua) {
        this.giaDongCua = giaDongCua;
    }

    public double getKlgdKhopLenh() {
        return klgdKhopLenh;
    }

    public void setKlgdKhopLenh(double klgdKhopLenh) {
        this.klgdKhopLenh = klgdKhopLenh;
    }
}
