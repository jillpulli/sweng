package agile;

import agile.util.DelimitedFileParser;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Assume args[0] is a relative path to the desired file.
        if (args.length == 0) {
            System.out.println("No filename given.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(
                "Cannot access '" + args[0] + "': No such file");
            return;
        }

        // System.out.println("\nReading data from file: " + file.getName());
        List<Map<String, String>> features =
            DelimitedFileParser.parse(file, "\t");

        // System.out.println("System ready with " + features.size() + " features.");

        addProgramKey(features);
        System.out.println(makeTable(features));
    }

    public static void addProgramKey(List<Map<String, String>> features) {
        String currentProgram = "";
        for (int i = 0, size = features.size(); i < size; i++) {
            Map<String, String> feature = features.get(i);
            if (feature.get("Level").equals("0"))
                currentProgram = feature.get("Key");
            feature.put("Program Key", currentProgram);
        }
    }

    public static String makeTable(List<Map<String, String>> features) {
        String table = "";
        for (Map<String, String> feature : features) {
            Iterator<String> it = feature.keySet().iterator();
            while (it.hasNext())
                table += feature.get(it.next()) + (it.hasNext() ? '\t' : '\n');
        }
        return table;
    }
}
