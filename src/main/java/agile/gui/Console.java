package agile.gui;

import agile.util.Printable;

import javafx.scene.control.TextArea;

public class Console implements Printable {

    private TextArea consoleText;

    public Console(TextArea consoleText) {
        this.consoleText = consoleText;
    }

    @Override
    public void println(String text) {
        consoleText.appendText(text + System.lineSeparator());
    }
}
