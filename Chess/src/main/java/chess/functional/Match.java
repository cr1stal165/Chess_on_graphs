package chess.functional;

import chess.board.Board;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Match extends Application{

    private HBox state;
    private static Match match;
    private BorderPane pane;
    private Board board;

    @Override
    public void start(Stage stage){
        match = this;
        pane = new BorderPane();
        Field field = new Field();
        createMenu();
        field.getGridPane().setAlignment(Pos.CENTER);
        pane.setCenter(field.getGridPane());
        stage.setResizable(false);
        stage.setScene(new Scene(pane, 600, 626));
        stage.show();
    }

    public void finishTheGame(Color color) {
        Stage stage = new Stage();
        Label text = new Label();
        text.setFont(new Font("Cambria", 40));
        text.setTextFill(Color.BROWN);
        text.setText((color == Color.WHITE ? "БЕЛЫЕ " : "ЧЕРНЫЕ ") + "ПРОИГРАЛИ");
        text.setAlignment(Pos.CENTER);
        match.state = new HBox(text);
        match.state.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(match.state, 400, 200));
        stage.show();
    }

    public void createMenu(){
        MenuBar menuBar = new MenuBar();
        Menu saveMenu = new Menu("Сохранить");
        Menu loadMenu = new Menu("Загрузить");

        saveMenu.setOnAction(actionEvent -> {

        });

        loadMenu.setOnAction(actionEvent -> {

        });
        menuBar.getMenus().addAll(saveMenu,loadMenu);
        pane.setTop(menuBar);
    }
}
