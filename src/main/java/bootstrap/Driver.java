package bootstrap;


import dataAnalysis.Chart;
import domain.SuspiciousUser;
import domain.SuspiciousUserListener;
import logFileAnalysis.LogAnalysis;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

import static bootstrap.GlobalConstants.*;

public class Driver {
    public static final Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig(args);
        configureLogging(applicationConfig.isDebugLogON()
                ,applicationConfig.getLogFileDirectory() +"/"+ EXECUTION_LOG_FILE_NAME );

        logger.info("analysis Started");
        try {
            Vector<SuspiciousUser> suspiciousUsers = new Vector<>();
            SuspiciousUserListener.fetchSuspiciousUser(suspiciousUsers, INFO_FILE);
            new Chart(suspiciousUsers,
                    applicationConfig.getAnalysisDirectory());
            LogAnalysis logAnalysis = new LogAnalysis();
            logAnalysis.getLogFileInfo();
        } catch (Exception e) {
            logger.error("Exception occurred", e);
        }

    }
    public static void configureLogging(boolean debug, String logFileName) {
        FileAppender fileAppender = new FileAppender();

        if (!debug) {
            fileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
        } else {
            fileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
        }

        fileAppender.setFile(logFileName);
        fileAppender.setLayout(new EnhancedPatternLayout("%-6d [%t] %-5p %c - %m%n"));
        fileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fileAppender);
        fileAppender.getFile();
    }
}
