package agile.util;

/**
 * Thrown to indicate there is a problem with a accessing or writing data to a
 * data table.
 */
public class TableException extends java.lang.RuntimeException {

    /**
     * Creates a TableException with the specified message.
     *
     * @param message the message to give to this TableException
     */
    public TableException(String message) {
        super(message);
    }
}
