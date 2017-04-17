package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sun.font.Script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by zdenek on 4/16/17.
 */
public class Output {
    Label output = new Label("");
    Float result;
    String mathExpression;

    public Output(GridPane gridPane) {
        gridPane.add(output, 0, 0, 4, 1);
        mathExpression = "";
    }

    public void outputAppend(String string) {
        mathExpression += string;
        output.setText(output.getText() + string);
    }

    public void outputResult() throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        output.setText(output.getText() + " = " + engine.eval(mathExpression));
        mathExpression = "";
    }



    public void clear() {
        output.setText("");
    }
}
