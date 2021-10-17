package dataAnalysis;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class ChartHelper {
    public static String getDay(String dateStr,String monthStr) {
        int date = Integer.parseInt(dateStr);
        int month = getMonth(monthStr);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        LocalDate localDate = LocalDate.of(year,month,date);
        return DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK)).toString();
    }

    private static int getMonth(String monthStr) {
        int month=0;
        switch (monthStr.toLowerCase()) {
            case "jan":
                month = 1;
                break;
            case "feb":
                month = 2;
                break;
            case "mar":
                month = 3;
                break;
            case "apr":
                month = 4;
                break;
            case "may":
                month = 5;
                break;
            case "jun":
                month = 6;
                break;
            case "jul":
                month = 7;
                break;
            case "aug":
                month = 8;
                break;
            case "sep":
                month = 9;
                break;
            case "oct":
                month = 10;
                break;
            case "nov":
                month = 11;
                break;
            case "dec":
                month = 12;
                break;
        }
        return month;
    }

    public static float timeInHr(String time) {
        String[] timeStr = time.split(":");
        float hour = Float.parseFloat(timeStr[0]);
        float minutes = Float.parseFloat(timeStr[1])/60;
        float seconds = Float.parseFloat(timeStr[2])/3600;
        return hour+minutes+seconds;
    }
}
