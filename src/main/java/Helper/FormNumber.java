package Helper;

public class FormNumber {
	public String getForm(double a) {
		String integer = String.valueOf((int) a);
		String result = new String();
		int d = integer.length() % 3;
		for (int i = 0; i < integer.length(); i++) {
			if (i % 3 == d && i != 0)
				result = result + ",";
			result = result + integer.charAt(i);

		}
		int thapphan = (int) ((a - (int) a) * 100);
		if(thapphan!=0)
		result = result + "." + thapphan;
		return result;
	}
}
