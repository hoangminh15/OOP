package Helper;

public class DateValidator {

    // Return value: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
    public int findDayOfWeek(int day, int month, int year){
        // Zeller's congruence algorithm
        int m = (month - 3 + 4800) % 4800;
        int y = (year + m / 12) % 400;
        m %= 12;
        return (y + y/4 - y/100 + (13 * m + 2) / 5 + day + 2) % 7;
    }
}
