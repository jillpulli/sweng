package agile.Gui;

import agile.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import agile.feature.ProgramManager;

import java.io.File;
import agile.util.DataTable;
import agile.ExportFile;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.List;
import javafx.geometry.HPos;
import agile.feature.FeatureFactory;
import agile.ExportFile;
import java.awt.event.ActionListener;

public class Controller
{
    AVT theView = new AVT();
    ProgramManager theModel = new ProgramManager();
    File importedFile;
    File exportFile;
    DataTable FeatPercentInMatrix;
    DataTable InOutMatrix;
    DataTable TotalSizeMatrix;
    SimpleLogger LOGGER = new SimpleLogger(System.out);

    public void getImported(File file)
    {
        List<FeatureRecord> records;
        try {
            LOGGER.info("Reading feature data from " +
                    file.getAbsolutePath());
            records = RecordsIO.importRecords(file);
        }
        catch (TableException ex) {
            LOGGER.error(ex.getMessage());
            return;
        }

        FeatureFactory.assemblePrograms(theModel,records,LOGGER);

    }

    public void getExported(File directory){
        for (ExportFile file1 : ExportFile.values())
            runExportThread(directory, file1, theModel);


    }

    private void runExportThread(File directory, ExportFile expFile,
                                        ProgramManager manager) {
        new Thread(() -> {
            File target = new File(directory, expFile.getName());
            RecordsIO.exportRecords(target, expFile.makeTable(manager));
            LOGGER.info("Exported " + target.getAbsolutePath());
        }).start();
    }

    /*
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

    public void getExportAllFile(File file)
    {
        exportFile = file;
        //runExportThread();
    }

    /
    public void exportCSV(DataTable matrix)
    {
        //ExportFile matrixCSV = new ExportFile(exportFile.getName(),matrix);
        //runExportRecords(exportFile.getPath(), matrix, theModel);
    }
    */
}