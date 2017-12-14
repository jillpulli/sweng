package agile.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import static javafx.geometry.Pos.CENTER_LEFT;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;

import java.io.File;
/*
 * Bug: Want to upload a new file after importing a file
 * ..
 * RU SURE PAGE ALL PREVIOUS MATRICES WILL BE FORGOTTEN UNLESS EXPORTED
 * NEEDS TO REST EVERYTHING
 */
public class GUI
{
    public static void display(Stage primaryStage)
    {
        Button ImportButton = new Button();
        ImportButton.setText("Upload");
        ImportButton.setStyle("-fx-padding: 15 40 15 40;");
        Button ExportButton = new Button();
        ExportButton.setText("Export");
        ExportButton.setStyle("-fx-padding: 15 40 15 40;");
        Controller controller = new Controller();
        // Border Declaration
        BorderPane border = new BorderPane();

        // Center Panel Declaration
        VBox center = new VBox();

        // Top Panel Declaration
        HBox top = new HBox();

        /*
         * Adds ImportButton and ExportButton to the Hbox
         */
        top.setPadding(new Insets(15, 18, 15, 18));
        top.setSpacing(40);
        top.setStyle("-fx-background-color:  #154c83;");
        Label titl = new Label("Agile Visualization Tool");
        titl.setFont(new Font("Arial",  22));
        titl.setTranslateY(15);
        titl.setTextFill(Color.web("#f5f5f5"));
        top.getChildren().add(titl);

        /*
        * This is the center
        */
        Text importMsg = new Text("Click import a CSV.");
        importMsg.setFont(Font.font("Times New Roman", 14));
        Text exportMsg = new Text("Click export all matrices.");
        exportMsg.setFont(Font.font("Times New Roman", 14));
        center.setPadding(new Insets(15, 50, 20, 63));
        center.setSpacing(15);
        center.setStyle("-fx-background-color:  #f5f5f5;");
        center.getChildren().add(importMsg);
        center.getChildren().add(ImportButton);
        center.getChildren().add(exportMsg);
        center.getChildren().add(ExportButton);
        center.setAlignment(CENTER_LEFT);

        /*
        * This is the panel for the main window
        */
        border.setTop(top);
        border.setCenter(center);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(265);
        window.setMinHeight(200);
        window.setResizable(false);

        Scene scene = new Scene(border, 265, 315);
        window.setScene(scene);
        window.showAndWait();




        ImportButton.setOnAction(e ->
        {
            File file = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open CSV File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extensionFilter);
            file = fileChooser.showOpenDialog(primaryStage);
            controller.getImported(file);
        });

        /*
         * Gives the Export Button Functionality
         * For now this should just add this to the arguments and call the controllers main passing these arguements
*/
        ExportButton.setOnAction(e ->
        {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            controller.getExported(selectedDirectory);
        });
    }
}