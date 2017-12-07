package GUI;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.TextAlignment;

public class ExportBox
{
    public static Label display (String title, String message)
    {
        String defaultPath = "C://Stephanie/Desktop/";

        final Label filePath = new Label();
        /*
         * Creates the Window also known as the primaryStage
         */
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        /*
         * Creates the buttons and features
         */
        Button exportButton = new Button("Export");
        exportButton.setStyle("-fx-padding: 10 30 10 30;");
        exportButton.setTooltip(new Tooltip("Transfers the matrix as a CSV to the location provided."));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-padding: 10 30 10 30;");
        cancelButton.setTooltip(new Tooltip("Exits out of the window."));

        TextField CSVPath = new TextField();
        CSVPath.setPromptText("CSV Export Path Here.");
        CSVPath.setPrefWidth(100);

        /*
         * Gives buttons their functionaility
         */
        exportButton.setOnAction( e ->
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

        /*
         * Where all the buttons are
         */
        VBox infoBox = new VBox();
        infoBox.getChildren().addAll( CSVPath, exportButton, cancelButton, filePath);
        infoBox.setSpacing(18);
        infoBox.setAlignment(Pos.CENTER);

        /*
         * Creates top box with message
         */
        HBox top = new HBox();
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #001480;");
        Text header = new Text(message);
        header.setTextAlignment(TextAlignment.CENTER);
        header.setStyle("-fx-text-fill: floralwhite;");
        header.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        top.getChildren().add(header);

        /*
         * Main GUI's design
         */
        BorderPane mainExportGUI = new BorderPane();
        mainExportGUI.setTop(top);
        mainExportGUI.setCenter(infoBox);

        Scene scene = new Scene(mainExportGUI, 500, 250);
        window.setScene(scene);
        window.showAndWait();

        return filePath;
    }
}
