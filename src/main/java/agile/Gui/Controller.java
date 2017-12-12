package agile.Gui;

import javafx.application.Application;
import javafx.stage.Stage;
import agile.feature.ProgramManager;
import agile.util.DataTable;
import java.io.File;
import java.util.List;
import agile.util.DataTable;
import agile.ExportFile;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.awt.event.ActionListener;
import javafx.geometry.HPos;

public class Controller
{
    AVT theView = new AVT();
    ProgramManager theModel = new ProgramManager();
    File importedFile;
    File exportFile;
    DataTable FeatPercentInMatrix;
    DataTable InOutMatrix;
    DataTable TotalSizeMatrix;

    public void getImported(File file)
    {
        importedFile = file;
    }

    private void fillGrid(DataTable matrixInfo, GridPane matrixPane)
    {
       String[] header = matrixInfo.getHeaders();
       List<String[]> body = matrixInfo.getBody();
        //get body gets the body of the matrix

        for (int i = 0; i < header.length; i++) {

            Label label = new Label(header[i]);
            label.setFont(Font.font("Verdana", 14));
            matrixPane.addRow(0, label);
        }

        //add body to gridpane
        int temp = 1;
        for (int j = 0; j < body.size(); j++)
        {
            temp += 1;
            for (int p = 0; p < body.get(j).length; p++)
            {

                Label rowlabel = new Label(body.get(j)[p]);
                GridPane.setHalignment(rowlabel, HPos.CENTER);
                matrixPane.addRow(temp, rowlabel);

            }

        }
    }

    public void makeFeatPercentInMatrix()
    {
        FeatPercentInMatrix = theModel.makeFeatPercentInMatrix(theModel);
    }

    public void makeInOutMatrix()
    {
        InOutMatrix = theModel.makeFeatPercentInMatrix(theModel);
    }

    public void getTotalSizeMatrix()
    {
        TotalSizeMatrix = theModel.makeFeatPercentInMatrix(theModel);
    }

    public void getExportFile(File file)
    {
        exportFile = file;
    }

    /*
     * This should export one type of matrix at a time
     */
    public void exportCSV(DataTable matrix)
    {
        //ExportFile matrixCSV = new ExportFile(exportFile.getName(),matrix);
        //runExportRecords(exportFile.getPath(), matrix, theModel);
    }
}