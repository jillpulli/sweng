package agile.Gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static javafx.geometry.Pos.CENTER;

/*
 * This is GUI FRONT END
 */
public class AVT
{
    private Controller controller;

    // Stage Field Declaration
    Stage primaryStage = new Stage();

    // Boolean Imported Declaration
    Boolean imported = false;

    // Image Declaration
    Image waitingHoneyBadger;

    // Button Declarations
    Button ImportButton = new Button();
    Button FTPercentInMatrixButton = new Button();
    Button TotalSizeMatrixButton = new Button();
    Button InOutMatrix = new Button();
    Button ExportButton = new Button();

    // Border Declaration
    BorderPane border = new BorderPane();

    // Left Panel Declaration
    VBox left = new VBox();

    // Right Panel Declaration
    VBox right = new VBox();

    // Center Panel Declaration
    StackPane center = new StackPane();

    // Top Panel Declaration
    HBox top = new HBox();

    // Bottom Panel Declaration
    HBox bottom = new HBox();

    /*
     * Constructor of Agile Visualization Tool
     */
    public AVT()
    {
        /*
         * Block makes each button unique button
         * adds spacing in between the buttons
         */
        ImportButton.setText("Upload CSV");
        ImportButton.setStyle("-fx-padding: 15 30 15 30;");
        FTPercentInMatrixButton.setText("Feature Percent In Matrix");
        FTPercentInMatrixButton.setStyle("-fx-padding: 15 22 15 22;");
        FTPercentInMatrixButton.setTooltip(new Tooltip("Give Honey Badger Cassandra this matrix to put together."));
        TotalSizeMatrixButton.setText("Total Size Matrix");
        TotalSizeMatrixButton.setStyle("-fx-padding: 15 48 15 48;");
        TotalSizeMatrixButton.setTooltip(new Tooltip("Give Honey Badger Cassandra this matrix to put together."));
        InOutMatrix.setText("In Out Matrix");
        InOutMatrix.setStyle("-fx-padding: 15 60 15 60;");
        InOutMatrix.setTooltip(new Tooltip("Give Honey Badger Cassandra this matrix to put together."));
        ExportButton.setText("Export CSV");
        ExportButton.setStyle("-fx-padding: 15 30 15 30;");

    }

    /*
     * Constructs the center panel as its default picture
     */
    public void defaultAVTCenter()
    {
        /*
         * This is just a place holder until a type of matrix is selected
         * Once a file is selected, this panel should populate with a matrix grid
         */
        // load the image
        waitingHoneyBadger = new Image("agile/HoneyBadgerWaiting.png", 750, 531, false, false);
        Label centerPhoto = new Label();
        centerPhoto.setGraphic(new ImageView(waitingHoneyBadger));
        centerPhoto.setTooltip(new Tooltip("Honey Badger Cassandra is waiting for a CSV to be uploaded.."));
        center.getChildren().add(centerPhoto);
    }

    public File getImportFile()
    {
        File file = null;

        if (!imported)
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open CSV File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extensionFilter);
            file = fileChooser.showOpenDialog(primaryStage);
            if (file != null)
                imported = true;
        }
        else
        {
            Boolean wantsToReImport = ReImportWarning.display();
            if(wantsToReImport)
            {
                // Resets the GUI
                border.setCenter(center);
                imported = false;
                // Does what file choose did if this was the first time importing a CSV
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open CSV File");
                FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                fileChooser.getExtensionFilters().add(extensionFilter);
                file = fileChooser.showOpenDialog(primaryStage);
                if (file != null)
                    imported = true;
            }
        }

        return file;
    }

    public File getExportFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export CSV File");
        File fileLocation = fileChooser.showSaveDialog(primaryStage);
        return fileLocation;
    }

    public BorderPane makeMatrix()
    {
        BorderPane matrix = new BorderPane();
        matrix.setPadding(new Insets(20, 35, 20, 20));
        //  bp.setTop(tp);

        // Where the matrix will go
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setAlignment(CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        matrix.setCenter(grid);

        //when cells are larger than size of panel
        ScrollPane sp = new ScrollPane(grid);
        matrix.setCenter(sp);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setFitToWidth(true);
        //addInfo(grid); <-- Controller does this in makeMatrixCenter
        /*controller.getMatrixDataTable(grid)*/

        return matrix;
    }


    public void display(Stage primaryStage) throws Exception
    {
        //primaryStage.setTitle("Agile Visualization Tool");

        /*
         * Left side of the main AVT page
         * Left side never changes so it should be created once.
         */
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setSpacing(20);
        left.setStyle("-fx-background-color:  #f5f5f5;");

        /*
         * Bottom of the main AVT page
         * Bottom side never changes so it should be created once
         */
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setSpacing(20);
        bottom.setStyle("-fx-background-color:  #f5f5f5;");

        /*
         *  Right Side of the Screen
         *  Adds all of the buttons to a Vbox panel
         *  Right Side Panel is static until GROUP CONSENSUS on CHECKBOX feature
         */
        right.setPadding(new Insets(10, 30, 10, 30));
        right.setSpacing(20);
        Text title = new Text("Pick A Type of Matrix");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        right.setStyle("-fx-background-color:  #f5f5f5;");
        right.getChildren().add(title);
        right.getChildren().add(FTPercentInMatrixButton);
        right.getChildren().add(TotalSizeMatrixButton);
        right.getChildren().add(InOutMatrix);
        right.setAlignment(Pos.BASELINE_LEFT);

        /*
         * Adds ImportButton and ExportButton to the Hbox
         */
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #154c83;");
        Label titl = new Label("Agile Visualization Tool");
        titl.setTextFill(Color.web("#f5f5f5"));
        top.getChildren().add(ImportButton);
        top.getChildren().add(ExportButton);

        //______________________________________________________________________________________________________________________________
        defaultAVTCenter();

        /*
         * This is the panel for the main window
         */
        border.setTop(top);
        border.setLeft(left); //<-- prob wont exist
        border.setCenter(center); // <--- will be like excel with tabs
        border.setRight(right);
        border.setBottom(bottom); //<-- Will prob just say Created by Honey Badgers

        Scene frontEndGUI = new Scene(border, 1031, 629);
        primaryStage.setScene(frontEndGUI);
        primaryStage.setResizable(false); // Keeps the user from resizing the main GUI
        primaryStage.show();

        /*
         * Gives the Import Button Functionality
         */
        ImportButton.setOnAction(e ->
        {
            File file = getImportFile();
            controller.getImported(file);
        });

        /*
         * Gives the Export Button Functionality
         * For now this should just add this to the arguments and call the controllers main passing these arguements
         */
        ExportButton.setOnAction(e ->
        {
            File file = getExportFile();
            controller.getExportFile(file);
        });

        /*
         * Gives functionality to the FeatPercentInMatrix
         */
        FTPercentInMatrixButton.setOnAction( e ->
        {
            if(imported)
            {
                BorderPane matrix = makeMatrix(); // <--This will be specific in Controller
                border.setCenter(matrix);
            }
            else
            {
                FilePathErrorBox.display();
            }
        });

        TotalSizeMatrixButton.setOnAction( e -> {
            if(imported)
            {
                BorderPane matrix = makeMatrix(); // <--This will be specific in Controller
                border.setCenter(matrix);
            }
            else
            {
                FilePathErrorBox.display();
            }
        });

        InOutMatrix.setOnAction( e -> {
            if(imported)
            {
                BorderPane matrix = makeMatrix(); // <--This will be specific in Controller
                border.setCenter(matrix);
            }
            else
            {
                FilePathErrorBox.display();
            }
        });
    }
}