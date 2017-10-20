package agile.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.function.Predicate;

public class TextIO<T> {

    private BufferedReader stdin;

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = "";
        try {
            input = stdin.readLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        if (input == null) {
            System.out.println();
            System.exit(0);
        }
        input = input.trim();
        return input;
    }

    public int getIntegerInput(String prompt, int low, int high) {
        int response = 0;
        boolean valid = false;
        do {
            try {
                response = Integer.parseInt(getInput(prompt));
                if (response >= low && response < high)
                    valid = true;
                else
                    System.out.println("\n\tError: Number out of range.\n");
            }
            catch (NumberFormatException ex) {
                System.out.println("\n\tError: Input must be a number.\n");
            }
        } while (! valid);
        return response;
    }

    public int getMenuInput(String prompt, String[] choices) {
        System.out.println(prompt);
        int len = choices.length;
        for (int i = 0; i < len; i++)
            System.out.println("\t" + (i + 1) + ". " + choices[i]);
        return getIntegerInput(">> Make your selection now", 1, len);
    }

    public void printUnorderedList(String[] items) {
        for (int i = 0, len = items.length; i < len; i++)
            System.out.println("\t* " + items[i]);
    }

    public void run(T object, Predicate<T> programFlow) {
        try (BufferedReader reader = new BufferedReader(
                                     new InputStreamReader(
                                     System.in))) {
            this.stdin = reader;
            while(programFlow.test(object));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
