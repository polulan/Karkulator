package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 800, 800);
        //scene.getStylesheets().add(Main.class.getResource("/home/zdenek/IdeaProjects/Calculator/src/sample/stylesheet.css").toExternalForm());
        primaryStage.setTitle("Karkulacka");
        ButtonSetter buttonSetter = new ButtonSetter();
        buttonSetter.setButtNum(gridPane);
        buttonSetter.setButtOper(gridPane);
        Output output = new Output(gridPane);
        buttonSetter.setButtonAction(output);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
