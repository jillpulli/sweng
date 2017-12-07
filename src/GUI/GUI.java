package GUI;

import javafx.application.Application;
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

/*
 * This is GUI FRONT END
 */
public class GUI extends Application
{
    //String[] args = new String[2];
    public static void main(String[] args)
    {
        launch(args);
    }

    Image waitingHoneyBadger;

    Button ImportButton = new Button();
    Button FTPercentInMatrixButton = new Button();
    Button TotalSizeMatrixButton = new Button();
    Button InOutMatrix = new Button();
    Button ExportButton = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Agile Visualization Tool");

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

        /*
         * Left side of the screen
         */
        VBox left = new VBox();
        left.setPadding(new Insets(10, 10, 10, 10));
        left.setSpacing(20);
        left.setStyle("-fx-background-color:  #cec8d2;");

        /*
         *  Right Side of the Screen
         *  Adds all of the buttons to a Vbox panel
         */
        VBox right = new VBox();
        right.setPadding(new Insets(10, 30, 10, 30));
        right.setSpacing(20);
        Text title = new Text("Pick A Type of Matrix");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        right.setStyle("-fx-background-color:  #cec8d2;");
        right.getChildren().add(title);
        right.getChildren().add(FTPercentInMatrixButton);
        right.getChildren().add(TotalSizeMatrixButton);
        right.getChildren().add(InOutMatrix);
        right.setAlignment(Pos.BASELINE_LEFT);

        /*
         * Adds ImportButton and ExportButton to the Hbox
         */
        HBox top = new HBox();
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #001480;");
        top.getChildren().add(ImportButton);
        top.getChildren().add(ExportButton);

        /*
         * Bottom of the screen
         */
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setSpacing(20);
        bottom.setStyle("-fx-background-color:  #cec8d2;");

        /*
         * This is just a place holder until a type of matrix is selected
         * Once a file is selected, this panel should populate with a matrix grid
         */
        StackPane center = new StackPane();
        // load the image
        waitingHoneyBadger = new Image("HoneyBadgerWaiting.png", 750, 531, false, false);
        Label centerPhoto = new Label();
        centerPhoto.setGraphic(new ImageView(waitingHoneyBadger));
        centerPhoto.setTooltip(new Tooltip("Honey Badger Cassandra is waiting for a CSV to be uploaded.."));
        center.getChildren().add(centerPhoto);

        /*
         * This is the panel for the main window
         */
        BorderPane border = new BorderPane();
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
            Label path = ImportBox.display("Import CSV File", "Type CSV Import Path Here.");
            String s = path.getText();
            System.out.println(s);
        });

        /*
         * Gives the Export Button Functionality
         * For now this should just add this to the arguments and call the controllers main passing these arguements
         */
        ExportButton.setOnAction(e ->
        {
            Label path = ExportBox.display("Export CSV File", "Type CSV Export Path Here.");
            String s = path.getText();
            System.out.println(s);
            //args[1] = s;
            //Main.main(args);
        });

    }
}