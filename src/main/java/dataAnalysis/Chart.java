package dataAnalysis;


import domain.SuspiciousUser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class Chart {

    public static void createPieChart(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
        DefaultPieDataset<String> datasetOfIP = new DefaultPieDataset<>();
        for (SuspiciousUser suspiciousUser : suspiciousUsers) {
            String ip = suspiciousUser.getIp();
            datasetOfIP.setValue(ip, suspiciousUser.getFrequency());
        }

        JFreeChart jFreeChartIp = ChartFactory.createPieChart(
                "IP",
                datasetOfIP,
                true,
                true,
                false
        );

        File pieChartIP = new File(analysisDirectory + "/ip.jpeg");
        ChartUtils.saveChartAsJPEG(pieChartIP,jFreeChartIp,1200,750);

        DefaultPieDataset<String> datasetOfCountry = new DefaultPieDataset<>();
        for (SuspiciousUser suspiciousUser : suspiciousUsers) {
            datasetOfCountry.setValue(suspiciousUser.getCountry(), suspiciousUser.getFrequency());
        }

        JFreeChart jFreeChartCountry = ChartFactory.createPieChart(
                "Countries",
                datasetOfCountry,
                true,
                true,
                false
        );

        File pieChartCountry = new File(analysisDirectory + "/countries.jpeg");
        ChartUtils.saveChartAsJPEG(pieChartCountry,jFreeChartCountry,1200,750);
        createBarGraph(suspiciousUsers,analysisDirectory);
    }

    public static void createBarGraph(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
        DefaultCategoryDataset attack = new DefaultCategoryDataset();

        for (SuspiciousUser suspiciousUser : suspiciousUsers) {
            ArrayList<String> timestamps = suspiciousUser.getTimestamps();
            for (String timestamp: timestamps) {
                String[] time = timestamp.split(";");
                String dayOfWeek = getDay(time[1],time[0]);
                Float timeInHr = timeInHr(time[2]);
                attack.addValue(timeInHr,dayOfWeek,"attack");
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Attacks",
                "Days",
                "attacks/day",
                attack,
                PlotOrientation.VERTICAL,
                true,true,false
        );
        File BarChart = new File( analysisDirectory + "/BarChart.jpeg" );
        ChartUtils.saveChartAsJPEG( BarChart , barChart , 1200 ,750 );

    }

    public static String getDay(String dateStr,String monthStr) {
        int date = Integer.parseInt(dateStr);
        int month = getMonth(monthStr);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(date+" "+month+" "+year);
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
