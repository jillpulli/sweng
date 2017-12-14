package agile.util;

/**
 * Represents a logger for outputting different status messages. The target of
 * the output is determined by the logger's PrintStream.
 */
public class SimpleLogger {

    private Printable out;

    /**
     * SimpleLogger Constructor.
     * Creates a SimpleLogger with the PrintStream. All messages will be
     * printed immediately with this stream.
     *
     * @param out the output stream
     */
    public SimpleLogger(Printable out) {
        this.out = out;
    }

    /**
     * Prints the specified message prepended by "ERROR:".
     *
     * @param message the message to print
     */
    public void error(String message) {
        log("ERROR: " + message);
    }

    /**
     * Prints the specified message prepended by "INFO:".
     *
     * @param message the message to print
     */
    public void info(String message) {
        log("INFO: " + message);
    }

    /**
     * Prints the specified message.
     *
     * @param message the message to print
     */
    public void log(String message) {
        out.println(message);
    }
}
