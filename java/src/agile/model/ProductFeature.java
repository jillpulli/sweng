package agile.model;

import java.util.Map;
import java.util.HashMap;

public class ProductFeature extends Feature
{
    Boolean isRoot;
    Map<String, TeamFeature> teamFTCollection = new HashMap<String, TeamFeature>();

    /*
    ** Product Feature Constructor
    */
    public ProductFeature(int s, String k, String d, String fv, Boolean rootStatus, HashMap<String, TeamFeature> teamFTs)
    {
        super(s,k,d,fv);
        isRoot = rootStatus;
        teamFTCollection = teamFTs;
    }

    public void printFeature()
    {
        System.out.println("This print will be specific to this type of a feature!!");
    }
}
