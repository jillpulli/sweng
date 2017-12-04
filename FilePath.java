package agile;

public class FilePath
{
    boolean validFilePath = true;
    String filePath;

    public FilePath(String path)
    {
        filePath = path;
        isPathValid(filePath);
    }

    private void isPathValid(String filePath)
    {
        // This method will need a parser to check whether the path is valif
        // Depending on boolean it should modify validFilePath..
        // It is key to keep this updated because it will used to
        // Trigger an alert window to enter a new file path string
        // or set default path.. hover will show the the text string of default path
    }
}
