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
import java.util.ArrayList;
import java.util.Vector;

public class Chart {
    public Chart(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
        createCharts(suspiciousUsers,analysisDirectory);
    }

    private void createCharts(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
        createIpBasedPieChart(suspiciousUsers,analysisDirectory);
        createCountryBasedPieChart(suspiciousUsers,analysisDirectory);
        createBarGraph(suspiciousUsers,analysisDirectory);
    }

    private void createIpBasedPieChart(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
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
    }

    private void createCountryBasedPieChart(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
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
    }


    private void createBarGraph(Vector<SuspiciousUser> suspiciousUsers, String analysisDirectory) throws IOException {
        DefaultCategoryDataset attack = new DefaultCategoryDataset();

        for (SuspiciousUser suspiciousUser : suspiciousUsers) {
            ArrayList<String> timestamps = suspiciousUser.getTimestamps();
            for (String timestamp: timestamps) {
                String[] time = timestamp.split(";");
                String dayOfWeek = ChartHelper.getDay(time[1],time[0]);
                Float timeInHr = ChartHelper.timeInHr(time[2]);
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
}
