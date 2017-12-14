package agile.util;

/**
 * Thrown to indicate there is a problem with a accessing or writing data to a
 * data table.
 */
public class TableException extends java.lang.RuntimeException {

    private int recordIndex = -1;

    /**
     * Creates a TableException with the specified message.
     *
     * @param message the message to give to this TableException
     */
    public TableException(String message) {
        super(message);
    }

    /**
     * Creates a TableException with the specified message snd the index of
     * the bad record in the data. The specified index is incremented by two.
     * This offset takes into a account the header and the 0 index of
     * collections in order to provide an accurate line number for feedback.
     * The recordIndex must be positive.
     */
    public TableException(String message, int recordIndex) {
        super(message);
        this.recordIndex = recordIndex + 2;
    }

    /**
     * Returns this TableException's message if a record index was not given
     * to it during construction. If a valid, positive record index was given
     * during construction, the returned message will be followed by
     * ": LINE <code>n</code>" where <code>n</code> is the line number.
     */
    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (recordIndex < 0)
            return message;
        return message + ": LINE " + recordIndex;
    }
}
