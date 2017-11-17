package agile;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Calculations
{
    public static HashMap<String, HashMap<String, Integer >> calc2(ArrayList< HashMap<String, String> > database)
    {
        HashMap<String,String> currentFeature = new HashMap<>();
        HashMap<String, Integer> deptProgFeats = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> deptwork = new HashMap<>();

        String ProgramFeatureName ="";
        int currentSize = 0;
        int prevSize = 0;
        int deptSize = 0;

        for(int i = 0; i < database.size(); i++) // READS THE WHOLE DATABASE(ARRAYLIST)
        {
            currentFeature = database.get(i);

            //checks if this is under the same Program Feature
            if(currentFeature.get("Program Key").equals(ProgramFeatureName))
            {
                //is this a product feature
                if(currentFeature.get("Level").equals(1)) // If this is a product
                {
                    //check if the department is already in the HashMap
                    if(deptwork.containsKey(currentFeature.get("DepartmentKey")))
                    {
                        //if same department and same program feature name, add current size to previous size
                        prevSize = ( deptProgFeats.get(ProgramFeatureName) );
                        currentSize = Integer.parseInt( currentFeature.get("Current Size") );
                        deptProgFeats.replace(ProgramFeatureName, currentSize);
                    }
                    else // if this is a new department
                    {
                        //add it to the deptwork HashMap
                        deptSize = Integer.parseInt(currentFeature.get("Size"));
                        deptProgFeats.put(ProgramFeatureName, (Integer)deptSize);
                        deptwork.put(currentFeature.get("Department"),deptProgFeats);
                    }
                }
            }
            else // Program feature name is different
            {
                ProgramFeatureName = currentFeature.get("Program Key");
            }

        }
        return deptwork;
    }

    public static void main (String[] args)
    {
        HashMap<String, String> h1 = new HashMap<>();
        h1.put("Feature Key", "CSLPRE-124");
        h1.put("Program Key", "CSLPRE-124");
        h1.put("Department Key", "CSLP_Radar_Enhancement");
        h1.put("Level", "0");
        h1.put("Current Size", "233");

        HashMap<String, String> h2 = new HashMap<>();
        h1.put("Feature Key", "ORTS-37410");
        h1.put("Program Key", "CSLPRE-124");
        h1.put("Department Key", "CSL_ORTS");
        h1.put("Level", "1");
        h1.put("Current Size", "20");

        HashMap<String, String> h3 = new HashMap<>();
        h1.put("Feature Key", "ORTS-37435");
        h1.put("Program Key", "CSLPRE-124");
        h1.put("Department Key", "CSL_ORTS");
        h1.put("Level", "1");
        h1.put("Current Size", "13");

        HashMap<String, String> h4 = new HashMap<>();
        h1.put("Feature Key", "CSLSPY-94638");
        h1.put("Program Key", "CSLPRE-124");
        h1.put("Department Key", "CSL_SPY");
        h1.put("Level", "0");
        h1.put("Current Size", "60");

        ArrayList<HashMap<String, String>> db = new ArrayList<HashMap<String, String>>();
        db.add(h1);
        db.add(h2);
        db.add(h3);
        db.add(h4);

        HashMap<String, HashMap<String, Integer >> finalHashMap = calc2(db);

    }
}
