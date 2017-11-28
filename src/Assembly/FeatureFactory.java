package agile.Assembly;

import agile.feature.FeatureAggregator;
import agile.feature.ProgramFeature;
import agile.feature.ProductFeature;
import agile.feature.TeamFeature;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class FeatureFactory
{
    /*
     * This will make program features and populate all of its children features in it
     * While adding it to FeatureAggregator
     */
    public FeatureAggregator generateFeatureTree(ArrayList<HashMap<String, String>> database, HashSet<String> departmentSet) // Will be a parameter of keysetdeptmartments and when you see 1 you add department to it
    { // Instead of being void you will return ftAggregator
        FeatureAggregator featureTree = new FeatureAggregator();
        HashMap<String, String> currHM;
        ProgramFeature currProgFT = new ProgramFeature("PLACEHOLDER", "WILL NEVER USE BC 1st CSV ENTRY IS PROGRAM FT", 0);
        ProductFeature currProdFT = new ProductFeature("FILLERBUSTER", 0, false);
        String currCSLProgram = " ";

        for(int i = 0; i < database.size(); i++) // Gets the HashMap inside each index
        {
            currHM = database.get(i);
            if(currHM.get("CSL Programs").equals(currCSLProgram))
            { // This means a program feature has been instaniated
                if (currHM.get("Level").equals(1))
                {
                    currProdFT = new ProductFeature(currHM.get("key"), Double.parseDouble(currHM.get("currentSize")), Boolean.valueOf(currHM.get("inCapacity")));
                    currProgFT.addFeature(currHM.get("Project"), currProdFT);
                    departmentSet.add(currHM.get("Project")); // Populates the departmentSet
                }
                else
                {
                    TeamFeature ft = new TeamFeature(currHM.get("key"), Double.parseDouble(currHM.get("currentSize")), Boolean.valueOf(currHM.get("inCapacity")));
                    currProdFT.addFeature(ft);
                }
            }
            else // If its a new CSL, add the previous Program Feature to the correct CSL
            {
                if(!currCSLProgram.equals(" ")) // Makes sure nothing is added in the zeroth iteration since product feature has yet to be built
                {
                    featureTree.addFeature(currCSLProgram, currProgFT);
                }

                // Makes the new Program Feature
                currProgFT = new ProgramFeature(currHM.get("key"), currHM.get("summary"), Integer.parseInt(currHM.get("priorityScore")));
            }
        }

        return featureTree;
    }

}
