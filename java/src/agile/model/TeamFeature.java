package agile.model;

public class TeamFeature extends Feature
{
    Boolean isLeaf;

    /*
    ** Team Feature Cconstructor
    */
    public TeamFeature(int s, String k, String d, String fv, Boolean leafStatus)
    {
        super(s,k,d,fv);
        isLeaf = leafStatus;
    }

    public void printFeature()
    {
        System.out.println("This print will be specific to this type of a feature!!");
    }
}
