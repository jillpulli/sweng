package agile.gui;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ImportBox
{
    public static Label display (String title, String message) {

        String defaultPath = "C://Stephanie/Desktop/";

        final Label filePath = new Label();
        Stage window = new Stage();

        TextField CSVPath = new TextField();
        CSVPath.setPrefWidth(100);
        CSVPath.setPromptText("CSV Import Path Here.");

        /*
         * Creates the primary stage which is referenced as Window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        /*
         * Creates the buttons and other features
         */
        Label label = new Label();
        label.setText(message);

        Button importButton = new Button("Import");
        importButton.setStyle("-fx-padding: 10 30 10 30;");
        importButton.setTooltip(new Tooltip("Retrieves the CSV located from the File Path provided."));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-padding: 10 30 10 30;");
        cancelButton.setTooltip(new Tooltip("Exits out of the window."));

        /*
         * Gives Buttons Functionality
         */
        importButton.setOnAction( e ->
        {
            String s;
            System.out.println(CSVPath.getText());
            if((CSVPath.getText() != null && !CSVPath.getText().isEmpty()))
            {
                filePath.setText(CSVPath.getText() + " is the path we are importing.");
                //s = CSVPath.getText();
            }
            else
            {
                filePath.setText("Invalid path. Setting to Default: " + defaultPath);
                CSVPath.setText(defaultPath);
            }


            window.close();
        }) ;

        cancelButton.setOnAction( e -> window.close());

        VBox infoBox = new VBox();
        infoBox.setSpacing(18);
        infoBox.getChildren().addAll(label, CSVPath, importButton, cancelButton,filePath);
        infoBox.setAlignment(Pos.CENTER);

        /*
         * Creates top box with message
         */
        HBox top = new HBox();
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #154c83;");
        Text header = new Text(message);
        header.setTextAlignment(TextAlignment.CENTER);
        header.setStyle("-fx-text-fill: #f5f5f5;");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        top.getChildren().add(header);

        /*
         * Main GUI's design
         */
        BorderPane mainImportGUI = new BorderPane();
        mainImportGUI.setTop(top);
        mainImportGUI.setCenter(infoBox);

        Scene scene = new Scene(mainImportGUI, 500, 250);
        window.setScene(scene);
        window.showAndWait();

        return filePath;

    }
}
