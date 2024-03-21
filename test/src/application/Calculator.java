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
        // テキストフィールドの内容を取得して、数値に変換する
        double value = Double.parseDouble(textField.getText()); // 注4
        // 前回のボタンの計算を行う
        switch (pressed.getText()) {
        case "+": // 加算
            result += value;
            break;
        case "-": // 減算
            result -= value;
            break;
        case "\u00D7": // 乗算
            result *= value;
            break;
        case "\u00F7": // 除算
            result /= value;
            break;
        default: // 入力値
            result = value;
        }
        // 計算結果を文字列に変換してテキストフィールドに書き込む
        if (result == (long) result) { // 整数の場合
            textField.setText(String.format("%d", (long) result));
        } else { // 整数以外の場合
            textField.setText(String.format("%g", result)); // 注5
        }
        // テキストフィールドにフォーカスする
        textField.requestFocus(); // 注6
        // 今回のボタンを識別する
        Button nowPressed = (Button) event.getSource();
        // 今回のボタンを記憶する
        pressed.setText(nowPressed.getText());
    }

    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
    }
}
