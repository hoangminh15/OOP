package Sentence;

public interface iSentence {
	public String generateSentence();
	public String getReferencePrice();
	public String giaCP();
	public String GiaTranSan(String dateData, String masan, String macophieu);
	public String giaCoPhieu(String dateData, String masan, String macophieu);
	public String khoiLuongGD();
	public String soSanhGD(String dateData, String masan, String macophieu);
	
}