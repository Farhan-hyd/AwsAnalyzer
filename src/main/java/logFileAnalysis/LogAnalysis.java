package logFileAnalysis;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static bootstrap.GlobalConstants.LOG_DIRECTORY;

public class LogAnalysis {
    private final HashMap<LogFile,Long> logFileInfo = new HashMap<>();
    private final String directory = LOG_DIRECTORY;

    public void getLogFileInfo() throws IOException {
            String[] pathname;
            File f = new File(directory);
            pathname = f.list();
            if (pathname != null) {
                for (String filename : pathname ) {
                    if(filename.endsWith(".log")) {
                        LogFile logFile = new LogFile(filename);
                        long length = Files.size(Paths.get(directory + "/" + filename));
                        if (logFileInfo.containsKey(logFile)) {
                            logFileInfo.put(new LogFile(filename),length);
                        } else {
                            long size = logFile.getSize();
                            float different = (float) length - (float) size;
                            float increase = (different / (float) length) * 100;
                            logFile.setIncreaseInSize(increase);
                        }
                    }
                }
            }
    }
}
