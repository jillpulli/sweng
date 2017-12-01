package agile;

import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.File;
import java.nio.file.*;
import java.io.File.*;

public class ExportBox
{
    public static Label display (String title, String message)
    {

        String defaultPath = "C://Stephanie/Desktop/";

        final Label filePath = new Label();
        Stage window = new Stage();

        TextField CSVPath = new TextField();
        CSVPath.setPromptText("CSV Path here");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        Label label = new Label();
        label.setText(message);
        Button exportButton = new Button("Import");
        Button cancelButton = new Button("Cancel");

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

        VBox layout = new VBox();
        layout.getChildren().addAll(label, CSVPath, exportButton, cancelButton,filePath);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.showAndWait();

        return filePath;
    }
}


