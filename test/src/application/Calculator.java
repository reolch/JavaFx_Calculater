package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Calculator extends Application {
	static double result; // 直前の計算結果
    static Label pressed; // 直前に押された計算ボタンの種類

    @Override
    public void start(Stage primaryStage) {

    }

    // 計算をする
    public static void calculate(ActionEvent event, TextField textField) {
    	try {
    		// テキストフィールドの内容を取得して、数値に変換する
            double value = Double.parseDouble(textField.getText());
            String operation = pressed.getText();
            result = performCalculate(result, value, operation);
            
            textField.setText(formatResult(result));
            

            // 今回のボタンを識別する
            Button nowPressed = (Button) event.getSource();
            // 今回のボタンを記憶する
            pressed.setText(nowPressed.getText());
    	} catch(NumberFormatException e) {
    		textField.setText("Error: " + e);
    	}
        
        
        // テキストフィールドにフォーカスする
        textField.requestFocus();
    }
    
    private static double performCalculate(double result, double value, String operation) {
    	switch (operation) {
	    	case "+": return result + value;
	        case "-": return result - value;
	        case "\u00D7": return result * value;
	        case "\u00F7": return result / value;
	        default: return value; // 初期値または演算子がない場合は入力値をそのまま返す
    	}
    }
    
    private static String formatResult(double result) {
        if (result == (long) result) { // 整数の場合
            return String.format("%d", (long) result);
        } else { // 浮動小数点数の場合
            return String.format("%g", result);
        }
    }

    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
    }
}
