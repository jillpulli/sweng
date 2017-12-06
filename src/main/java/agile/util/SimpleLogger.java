package agile.util;

import java.io.PrintStream;

public class SimpleLogger {

    private PrintStream out;

    public SimpleLogger(PrintStream out) {
        this.out = out;
    }

    public void error(String message) {
        log("ERROR: " + message);
    }

    public void info(String message) {
        log("INFO: " + message);
    }

    public void log(String message) {
        out.println(message);
    }

    public void warning(String message) {
        log("WARNING: " + message);
    }
}
