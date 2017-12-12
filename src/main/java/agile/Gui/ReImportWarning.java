package agile.Gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/*
 *  This window will show up as a warning
 *  Previous data will not be stored unless exported
 *
 */
public class ReImportWarning
{
    public static boolean display()
    {
        Boolean wantsToReImport = false;
        TextField boolString = new TextField();
        Button reimport = new Button("Import");
        reimport.setStyle("-fx-padding: 10 30 10 30;");

        Button cancel = new Button("Close");
        cancel.setStyle("-fx-padding: 10 30 10 30;");


        // Image HoneyBadgerWaiting = new Image("/src/main/HoneyBadgerFlags.png", 130, 130, false, false);
        //Label picture = new Label();
        // picture.setGraphic(new ImageView(HoneyBadgerWaiting));

        /*
         * This is Top
         */
        HBox top = new HBox();
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #154c83;");

        VBox centerCenter = new VBox();
        centerCenter.setSpacing(18);
        Text msg = new Text("      Are you sure you want to Import again? \n      Any matrices created will disappear forever. \n      If you would like to keep some matrices, \n      Export them before importing again. \n");
        msg.setFont(Font.font("Times New Roman", 14));
        centerCenter.setStyle("-fx-background-color:  #f5f5f5;");
        centerCenter.getChildren().add(msg);
        centerCenter.getChildren().add(reimport);
        centerCenter.getChildren().add(cancel);
        centerCenter.setAlignment(Pos.CENTER);

        VBox rightCenter = new VBox();
        rightCenter.setPadding(new Insets(10, 10, 10, 10));
        rightCenter.setSpacing(18);
        //rightCenter.getChildren().add(picture);

        BorderPane center = new BorderPane();
        center.setRight(rightCenter);
        center.setCenter(centerCenter);

        /*
         * This is bottom
         */
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setSpacing(15);
        bottom.setStyle("-fx-background-color:  #f5f5f5");

        /*
        * This is the panel for the main window
        */
        BorderPane border = new BorderPane();
        border.setTop(top);
        border.setCenter(center);
        border.setBottom(bottom);


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(200);

        /*
         * Gives the functionality to cancel button
         */
        cancel.setOnAction( e -> window.close());

        /*
         * Gives the functionality to  button
         */
        reimport.setOnAction( e -> {
            boolString.setText("true");
            window.close();
        });

        Scene scene = new Scene(border, 525, 350);
        window.setScene(scene);
        window.showAndWait();

        wantsToReImport = Boolean.parseBoolean(boolString.getText());
        return  wantsToReImport;
    }
}
