package agile.model;

public abstract class Feature
{
    // Attributes
    int size;
    String key;
    String department; // This is project in the csv put for coding reasons we will call it what it actually is
    String fixVersion ; // This is whether it is in or out of capacity

    /*
    ** An abstract feature will never be instaniated but its children must at least fill in this
    */
    // Constructor
    public Feature( int s, String k, String d, String fv)
    {
        size = s;
        key = k;
        department = d;
        fixVersion = fv;
    }

    // Accessors
    public int getSize()
    {
        return size;
    }

    public String getKey()
    {
        return key;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getFixVersion()
    {
        return fixVersion;
    }

    public Boolean isIncapacity()
    {
        if(fixVersion.equals("in_capacity"))
            return true;
        else
            return false;
    }

    // This abstract method must be implemented in by its children
    public abstract void printFeature();

}
