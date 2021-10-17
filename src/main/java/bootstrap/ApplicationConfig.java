package bootstrap;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ApplicationConfig {
    private final String[] args;
    private final boolean debugLog;
    private final String analysisDirectory;
    private final String logFileDirectory;

    public ApplicationConfig(String[] args) {
        this.args = args;
        Namespace namespace = buildNamespace(args);
        this.analysisDirectory = namespace.getString("analysis.directory.path");
        this.logFileDirectory = namespace.getString("log.file.directory");
        this.debugLog = namespace.getBoolean("debug.log.enabled");
    }

    public String[] getArgs() {
        return args;
    }

    public String getAnalysisDirectory() {
        return analysisDirectory;
    }

    public String getLogFileDirectory() {
        return logFileDirectory;
    }

    public boolean isDebugLogON() {
        return debugLog;
    }

    public Namespace buildNamespace(String[] args) {
        Namespace namespace = null;
        ArgumentParser argumentParser = ArgumentParsers.newFor("It is the AWS analyzer").build().defaultHelp(true)
                .description("It is used to analyze the auth.log file of AWS machine");
        argumentParser.addArgument("--debug.log.enabled").type(Boolean.class).setDefault(false)
                .help("boolean to determine if debug logging is enabled.");
        argumentParser.addArgument("--analysis.directory.path").type(String.class).setDefault(args[0])
                .help("Path of the Analysis directory directory.");
        argumentParser.addArgument("--log.file.directory").type(String.class).setDefault(args[1])
                .help("Path of the log file directory.");
        try {
            namespace = argumentParser.parseArgs(args);
        } catch (ArgumentParserException e) {
            e.printStackTrace();
        }
        return namespace;
    }
}
