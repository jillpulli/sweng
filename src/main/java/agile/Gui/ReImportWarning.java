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

/*
 *  This window will show up as a warning
 *  Previous data will not be stored unless exported
 *
 */
public class ReImportWarning
{
    public static void display()
    {
        Button okayButton = new Button("close");
        okayButton.setStyle("-fx-padding: 10 30 10 30;");
        okayButton.setTooltip(new Tooltip(".."));

        Image HoneyBadgerWaiting = new Image("src/main/HoneyBadgerFlags.png", 130, 130, false, false);
        Label picture = new Label();
        picture.setGraphic(new ImageView(HoneyBadgerWaiting));

        /*
         * This is Top
         */
        HBox top = new HBox();
        top.setPadding(new Insets(15, 10, 15, 10));
        top.setSpacing(15);
        top.setStyle("-fx-background-color:  #154c83;");

        /*
        * This is the center
        */
        VBox leftCenter = new VBox();
        leftCenter.setSpacing(18);
        leftCenter.getChildren().add(picture);

        VBox centerCenter = new VBox();
        centerCenter.setSpacing(18);
        Text msg = new Text("Error: No CSV has been uploaded.");
        msg.setFont(Font.font("Times New Roman", 14));
        centerCenter.setStyle("-fx-background-color:  #f5f5f5;");
        centerCenter.getChildren().add(msg);
        Text msg2 = new Text("Honey Badger Cassandra is confused.");
        msg2.setFont(Font.font("Times New Roman", 14));
        centerCenter.setStyle("-fx-background-color:  #f5f5f5;");
        centerCenter.getChildren().add(msg2);
        centerCenter.getChildren().add(okayButton);
        centerCenter.setAlignment(Pos.CENTER);

        VBox rightCenter = new VBox();
        rightCenter.setPadding(new Insets(10, 10, 10, 10));
        rightCenter.setSpacing(18);
        rightCenter.getChildren().add(picture);

        BorderPane center = new BorderPane();
        center.setRight(rightCenter);
        center.setLeft(leftCenter);
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

        Scene scene = new Scene(border, 400, 200);
        window.setScene(scene);
        window.showAndWait();

        /*
         * Give the buttons functionality
         */
        okayButton.setOnAction( e ->window.close());
    }
}
