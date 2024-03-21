package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CounterApp extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        // ラベルを作成する
        Label label = new Label("CounterApp");
        // テキストフィールドを作成する
        TextField textField = new TextField("0");
        // テキストフィールドの優先桁数を20に設定する
        textField.setPrefColumnCount(20);
        // ボタンを作成する
        Button button = new Button("+1");
        // ボタンのアクションを登録する
        button.setOnAction((ActionEvent event) -> {
            // テキストフィールドの内容を取得して、数値に変換する
            int value = Integer.parseInt(textField.getText());
            // 数値を更新して、テキストフィールドに書き込む
            textField.setText(Integer.toString(++value));
        });
        // VBoxペインを作成し、作成した部品を配置する
        VBox vbox = new VBox(label, textField, button);
        // シーンを作成し、ペインに入れる
        Scene scene = new Scene(vbox);
        // ステージにVBoxペインを入れる
        primaryStage.setScene(scene);
        // ステージのタイトルバーを設定する
        primaryStage.setTitle("CounterApp");
        // ステージを表示する
        primaryStage.show();
    }

    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
    }

}
