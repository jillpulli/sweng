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
            String level = feature.get("Level");
            if (level.equals("0"))
                currentProgram = feature.get("Key");
            feature.put("Program Key", currentProgram);
            int next = i + 1;
            if (next == size && !level.equals("0"))
                feature.put("Leaf", "Leaf");
            else if (level.compareTo(features.get(next).get("Level")) < 0)
                feature.put("Leaf", "Root");
            else
                feature.put("Leaf", "Leaf");
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

/*
    public static Map<String, Map<String, Integer>> calculateInCapacity(
            List<Map<String, String>> features)
        Map<String, Map<String, Integer>> sumInCapacity = new HashMap<>();
        for (Map<String, String> feature : features) {
            if (feature.get("Leaf").equals("Leaf") &&
                    feature.get("Fix Version/s").contains("InCapacity")) {
                String program = feature.get("Program Key");
                String department = feature.get("Project");
                if (!sumInCapacity.containsKey(program)) {
                    sumInCapacity.put(program, new HashMap<String, Integer>());
                    sumInCapacity.get(program).put(department, 0);
                }
            }
*/
}
