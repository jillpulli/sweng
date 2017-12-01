package agile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GUI extends Application
{
    String[] args = new String[2];
    public static void main(String[] args)
    {
        launch(args);
    }

    // private programManager Matrices does this make all three of them
    Stage window;
    Scene scene1, scene2;

    Button ImportButton;
    Button FTPercentInMatrixButton;
    Button TotalSizeMatrixButton;
    Button InOutMatrix;
    Button ExportButton;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Agile Visualization Tool");

        ImportButton = new Button();
        ImportButton.setText("Upload CSV");
        FTPercentInMatrixButton = new Button();
        FTPercentInMatrixButton.setText("Feature Percent In Matrix");
        TotalSizeMatrixButton = new Button();
        TotalSizeMatrixButton.setText("Total Size Matrix");
        InOutMatrix = new Button();
        InOutMatrix.setText("In Out Matrix");
        ExportButton = new Button();
        ExportButton.setText("Export");

        window = primaryStage;

        ImportButton.setOnAction(e ->
        {
            Label path = ImportBox.display("Import CSV File", "Type the CSV Path here.");
            String s = path.getText();
            args[0] = s;
            System.out.println(s);
        });

        /*
         * For now this should just add this to the arguments and call the controllers main passing these arguements
         */
        ExportButton.setOnAction(e ->
        {
            Label path = ExportBox.display("Export CSV File", "Type the CSV Path here.");
            String s = path.getText();
            System.out.println(s);

            args[1] = s;

            Main.main(args);
        });

        VBox layout = new VBox();
        layout.getChildren().add(ImportButton);
        layout.getChildren().add(ExportButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 500, 300);
        primaryStage.setScene(scene1);
        primaryStage.show();

        // Button on import window two
        //Button uploadCSVPathButton = new Button("Upload CSV Path");
        //uploadCSVPathButton.setOnAction(e -> primaryStage.setScene(scene1));

        ////layout 2
       // StackPane layout2 = new StackPane();
       // layout2.getChildren().add(uploadCSVPathButton);
       // scene2 = new Scene(layout2, 400, 200);

        window.setScene(scene1);
        window.setTitle("Agile Visualization Tool");
        window.show();
    }
}
