package chess.game;

import chess.board.CellPosition;
import chess.functional.Field;
import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI {

    public GuiCells setGraphicCellAnimation(Field field, char col, int row) {
        GuiCells cell = new GuiCells(new CellPosition(col, row), field.getBoard().getCell(new CellPosition(col, row)).getFigure() == null ? null :  field.getBoard().getCell(new CellPosition(col, row)).getFigure().getImage());
        cell.cellsFunctional(field);
        return cell;
    }

    public void showFigures(Color color, CellPosition cellPosition, Field field) {
        Stage stage = new Stage();
        Queen queen = new Queen(color, cellPosition);
        Bishop bishop = new Bishop(color, cellPosition);
        Knight knight = new Knight(color, cellPosition);
        Rook rook = new Rook(color, cellPosition);
        Button queenButton = new Button();
        queenButton.setGraphic(new ImageView(queen.getImage()));
        Button bishopButton = new Button();
        bishopButton.setGraphic(new ImageView(bishop.getImage()));
        Button knightButton = new Button();
        knightButton.setGraphic(new ImageView(knight.getImage()));
        Button rookButton = new Button();
        rookButton.setGraphic(new ImageView(rook.getImage()));
        queenButton.setOnAction(actionEvent -> {
            field.getBoard().getCell(cellPosition).setFigure(queen);
            for (GuiCells gc : field.getCells()) {
                if (gc.getPosition().equals(cellPosition)) {
                    gc.setImage(field.getBoard().getCell(cellPosition).getFigure().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        bishopButton.setOnAction(actionEvent -> {
            field.getBoard().getCell(cellPosition).setFigure(bishop);
            for (GuiCells gc : field.getCells()) {
                if (gc.getPosition().equals(cellPosition)) {
                    gc.setImage(field.getBoard().getCell(cellPosition).getFigure().getImage());
                }
            }

            stage.close();
            actionEvent.consume();
        });
        knightButton.setOnAction(actionEvent -> {
            field.getBoard().getCell(cellPosition).setFigure(knight);
            for (GuiCells gc : field.getCells()) {
                if (gc.getPosition().equals(cellPosition)) {
                    gc.setImage(field.getBoard().getCell(cellPosition).getFigure().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        rookButton.setOnAction(actionEvent -> {
            field.getBoard().getCell(cellPosition).setFigure(rook);
            for (GuiCells gc : field.getCells()) {
                if (gc.getPosition().equals(cellPosition)) {
                    gc.setImage(field.getBoard().getCell(cellPosition).getFigure().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(queenButton, bishopButton, knightButton, rookButton);
        stage.setScene((new Scene(hBox, 500, 200)));
        stage.setResizable(false);
        stage.show();
    }
}
