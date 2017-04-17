package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zdenek on 4/14/17.
 */
public class ButtonSetter {

    public List<Button> buttonList = new ArrayList<>();
    public List<Button> buttonOperList = new ArrayList<>();
    public Button c = new Button("C");


    public void setButtNum(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);

        //Stream.generate(Button::new).limit(8);

        buttonList.add(new Button(Integer.toString(buttonList.size())));
        gridPane.add(buttonList.get(0), 0, 4);
        gridPane.add(c, 1, 4);




        for(Integer i = 3; i > 0; i--) {
            for(Integer j = 0; j < 3; j++) {
                buttonList.add(new Button(Integer.toString(buttonList.size())));
                gridPane.add(buttonList.get(buttonList.size() - 1), j, i);
            }
        }
    }

    public void setButtOper(GridPane gridPane) {
        List<String> strings = new ArrayList<>(Arrays.asList("(", ")", ".", "=", "/", "*", "-", "+"));
        strings.forEach(str -> buttonOperList.add(new Button(str)));

        List<Button> horizontalOper = buttonOperList.stream().filter(button -> buttonOperList.indexOf(button) < 4)
                .collect(Collectors.toList());
        horizontalOper.forEach(button -> gridPane.add(button, horizontalOper.indexOf(button), 5));

        List<Button> verticalOper = buttonOperList.stream().filter(button -> buttonOperList.indexOf(button) > 3)
                .collect(Collectors.toList());
        verticalOper.forEach(button -> gridPane.add(button, 3, 4 - verticalOper.indexOf(button)));

    }

    public void setButtonAction(Output output) {

        c.setOnAction(e -> output.clear());



        buttonList.forEach(button -> button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                output.outputAppend(button.getText());
            }
        }));

        buttonOperList.forEach(button -> button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (button.getText() == "=") {
                    try {
                        output.outputResult();
                    }
                    catch (ScriptException se) {
                        System.out.println("Catched exception");
                    }
                }
                else {
                        output.outputAppend(button.getText());
                }
            }
        }));
    }


}
