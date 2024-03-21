package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
	static double result; // 直前の計算結果
    static Label pressed; // 直前に押された計算ボタンの種類

    @Override
    public void start(Stage primaryStage) {
        // ラベルを作成する
        Label label = new Label("Calculator");
        // ラベルの優先幅を100にする
        label.setPrefWidth(100); // 注1
        // テキストフィールドを作成する
        TextField textField = new TextField("0");
        // テキストフィールドの優先桁数を20に設定する
        textField.setPrefColumnCount(20); // 注2
        // 計算ボタンの記号を準備する
        String[] operator = { "+", "-", "\u00D7", "\u00F7", "=" }; // ＋－×÷＝
        // 5個の計算ボタンを作成する
        Button[] button = new Button[5];
        for (int i = 0; i < 5; i++) {
            // ボタンをインスタンス化する
            button[i] = new Button(operator[i]);
            //  ボタンのアクションを登録する
            button[i].setOnAction(event -> calculate(event, textField));
            // ボタンの優先幅を30に設定する
            button[i].setPrefWidth(30); // 注3
        }
        // 直前に押されたボタンを表示するラベルを作成する
        pressed = new Label();
        // HBoxペインを作成し、２つのラベルを配置する
        HBox hbox1 = new HBox(label, pressed);
        // HBoxペインを作成し、計算ボタンを配置する
        HBox hbox2 = new HBox(button);
        // VBoxペインを作成し、作成した部品を配置する
        VBox vbox = new VBox(hbox1, textField, hbox2);
        // シーンを作成し、ペインに入れる
        Scene scene = new Scene(vbox);
        // ステージにVBoxペインを入れる
        primaryStage.setScene(scene);
        // ステージのタイトルバーを設定する
        primaryStage.setTitle("Calculator");
        // ステージを表示する
        primaryStage.show();
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
