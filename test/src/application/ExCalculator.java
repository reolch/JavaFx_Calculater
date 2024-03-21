package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExCalculator extends Calculator {
	
	static boolean isTyping = false;
	
	@Override
    public void start(Stage primaryStage) {
		
		// ラベルを作成する
	    Label label = new Label("Calculator");
	    label.setStyle("-fx-background-color: #FF0000;"); // 赤色の背景を設定
	    // テキストフィールドを作成する
	    TextField textField = new TextField("0");
	    // テキストフィールドを右詰めにする
	    textField.setAlignment(Pos.CENTER_RIGHT);
	    // テキストフィールドのフォントサイズを18に、優先高を40にする
	    textField.setFont(new Font(18));
	    textField.setPrefHeight(40);
	    // ボタンの表示記号を準備する
	    String[] keyTop = {
	            "7", "8", "9", "CE", "AC",
	            "4", "5", "6", "+", "-",
	            "1", "2", "3", "\u00D7", "\u00F7",
	            "0", "\u00B7", "+/-", "BS", "=",
	    };
	    // 20個のボタンを作成する
	    Button[] button = new Button[20];
	    
	    for (int i = 0; i < 20; i++) {
            // ボタンをインスタンス化する
            button[i] = new Button(keyTop[i]);
            //  ボタンのアクションを登録する
            if (i == 8 || i == 9 || i == 13 || i == 14 || i == 19) { // 計算ボタン
                button[i].setOnAction(event -> {
                    // 入力中を解除する
                	isTyping = false; // 注1
                    // 計算ボタンの処理を行う
                    calculate(event, textField);
                });
            } else { // 入力ボタンとクリアボタン
                button[i].setOnAction(event -> {
                    // 入力中でなければ、クリアエントリをして入力状態にする
                    if (!isTyping) { // 注2
                    	isTyping = true;
                        textField.setText("0");
                    }
                    // 入力ボタンの処理を行う
                    input(event, textField);
                });
            }
            // ボタンのフォントサイズを14に、最小サイズを40x40に設定する
            button[i].setFont(new Font(14));
            button[i].setMinSize(40, 40);
	    }
        // タイルペイン(hgap=vgap=6)を作成する
        TilePane tilePane = new TilePane(6, 6, button);
        
        // 直前に押されたボタンを表示するラベルを作成する
        pressed = new Label("");
        pressed.setStyle("-fx-background-color: #0000FF;"); // 青色の背景を設定
        
        
        // HBoxペインを作成し、２つのラベルを配置する
        HBox hbox1 = new HBox(20, label, pressed);
        // HBoxをセンタリングする
        hbox1.setAlignment(Pos.CENTER);
        // VBoxペイン(spacing=6)を作成し、作成した部品を配置する
        VBox vbox = new VBox(6, hbox1, textField, tilePane);
        // VBoxペインのパディングを6にする
        vbox.setPadding(new Insets(6));
        // シーンを作成し、ペインに入れる
        Scene scene = new Scene(vbox);
        primaryStage.setResizable(false);
        // ステージにVBoxペインを入れる
        primaryStage.setScene(scene);
        // ステージのタイトルバーを設定する
        primaryStage.setTitle("Calculator");
        // ステージを表示する
        primaryStage.show();
	}
	    
	 // 入力ボタンとクリアボタンの処理を行う
	  public static void input(ActionEvent event, TextField textField) {
	        // 今回のボタンを識別する
	        Button nowPressed = (Button) event.getSource();
	        String key = nowPressed.getText();
	        // クリアボタの処理を行う
	        switch (key) {
	        case "AC": // オールクリア
	            result = 0.;
	            pressed.setText("");
	        case "CE": // クリアエントリ
	            textField.setText("0");
	            return;
	        }
	        // 現在のテキストフィールドの内容を取得する
	        String s = textField.getText();
	        // 英文字が含まれていれば、何もしない
	        if (s.matches(".*[a-zA-Z].*")) { // 注3
	            return;
	        }
	        // 入力ボタンの処理を行う
	        switch (key) {
	        case "BS": // バックスペース
	            if (s.length() == 1 || s.length() == 2 && s.charAt(0) == '-') {
	                textField.setText("0");
	            } else {
	                textField.setText(s.substring(0, s.length() - 1));
	            }
	            return;
	        case "+/-": // 符号反転
	            if (s.charAt(0) == '-') {
	                textField.setText(s.substring(1));
	            } else if (!s.equals("0")) {
	                textField.setText("-" + s);
	            }
	            return;
	        case "\u00B7": // 小数点
	            if (!s.contains(".")) {
	                textField.setText(s + ".");
	            }
	            return;
	        default: // 0-9の数字
	            if (!s.equals("0")) {
	                textField.setText(s + key);
	            } else {
	                textField.setText(key);
	            }
	        }
	    }
    
    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
    }
}
