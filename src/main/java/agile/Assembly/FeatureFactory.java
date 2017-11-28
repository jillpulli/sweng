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
        ProgramFeature currProgFT = null;
        ProductFeature currProdFT = null;
        TeamFeature currTeamFT = null;
        String currCSLProgram = " ";

        for (int i = 0; i < database.size(); i++) // Gets the HashMap inside each index
        {
            currHM = database.get(i);
            if (currHM.containsKey("CSL Programs"))
            {
                if (currHM.get("CSL Programs").equals(currCSLProgram))
                { // This means a program feature has been instaniated
                    if (currHM.get("Level").equals(1))
                    {
                        createProdFT(currHM, currProdFT);
                        currProgFT.addFeature(currHM.get("Project"), currProdFT);

                        // Populates the departmentSet
                        departmentSet.add(currHM.get("Project"));
                    }
                    else if (currHM.get("Level").equals(2))
                    {
                        createTeamFT(currHM, currTeamFT);
                        currProdFT.addFeature(currTeamFT);
                    }
                    else
                    {
                        // If it is some other number it is an Feature Object outside of current existence
                    }
                } else // If its a new CSL, add the previous Program Feature to the correct CSL
                {
                    if (currCSLProgram != null) // Makes sure nothing is added in the zeroth iteration since product feature has yet to be built
                    {
                        featureTree.addFeature(currCSLProgram, currProgFT);
                    }

                    // Makes the new Program Feature
                    createProgFT(currHM, currProgFT);
                }
            }
        }

        return featureTree;
    }

    private void createProgFT(HashMap<String, String> hm, ProgramFeature progFT)
    {
        if(hm.containsKey("key") && hm.containsKey("summary") && hm.containsKey("priorityscore"))
        {
            progFT = new ProgramFeature(hm.get("key"), hm.get("summary"), Integer.parseInt(hm.get("priorityScore")));
        }
    }

    private void createProdFT(HashMap<String,String> hm, ProductFeature prodFT)
    {
        if(hm.containsKey("key") && hm.containsKey("currentSize") && hm.containsKey("inCapacity"))
        {
            prodFT = new ProductFeature(hm.get("key"), Double.parseDouble(hm.get("currentSize")), Boolean.valueOf(hm.get("inCapacity")));
        }
    }

    private void createTeamFT(HashMap<String,String> hm, TeamFeature teamFT)
    {
        if(hm.containsKey("key") && hm.containsKey("currentSize") && hm.containsKey("inCapacity"))
        {
            teamFT = new TeamFeature(hm.get("key"), Double.parseDouble(hm.get("currentSize")), Boolean.valueOf(hm.get("inCapacity")));
        }
    }
}