package bootstrap;


import dataAnalysis.Chart;
import domain.SuspiciousUser;
import domain.SuspiciousUserListener;
import logFileAnalysis.LogAnalysis;

import java.util.Vector;

import static bootstrap.GlobalConstants.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        Vector<SuspiciousUser> suspiciousUsers = new Vector<>();
        SuspiciousUserListener.fetchSuspiciousUser(suspiciousUsers, INFO_FILE);
        Chart.createPieChart(suspiciousUsers, ANALYSIS_DIRECTORY);
        LogAnalysis logAnalysis = new LogAnalysis();
        logAnalysis.getLogFileInfo();
    }
}
