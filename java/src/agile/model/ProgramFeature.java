package agile.model;

import java.util.Map;
import java.util.HashMap;

public class ProgramFeature extends Feature
{
    // Attributes

    String summary;
    int priorityScore;
    String CSLProgram;
    int totalSize;
    Map<String, ProductFeature> departments = new HashMap<String, ProductFeature>();

    /*
    ** This is Program Features Constructor
    */
    public ProgramFeature( String k, String d, String fv, String summary1, int pS, String cslp, int tS, Map<String, ProductFeature> dept)
    {
        super(k,d,fv);
        summary = summary1;
        priorityScore = pS;
        CSLProgram = cslp;
        totalSize = tS;
        departments = dept;
        // Insert size must be implemented here!!
    }

    // Accessor Methods
    public String getSummary()
    {
        return summary;
    }

    public int getPriorityScore()
    {
        return priorityScore;
    }

    public String getCSLProgram()
    {
        return CSLProgram;
    }

    public int getTotalSize()
    {
        return totalSize;
    }

    public Map<String, ProductFeature> returnDepartments()
    {
        return departments;
    }

    public void printFeature()
    {
        System.out.println("This print will be specific to this type of a feature!!");
    }

    // Modifier Methods

    public void calcSize()
    {
        
    }

    /*
    * The sum of all of the productFts without children and the in capacity team features of productFts with team features
    * divided by the size of the program feature
    */
    public void calculatePercentInCapacity()
    {

    }
}