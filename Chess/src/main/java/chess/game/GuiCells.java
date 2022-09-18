package chess.game;

import chess.board.CellPosition;
import chess.functional.Field;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import chess.board.Cell;
import chess.board.Figure;

import java.util.LinkedList;

public class GuiCells extends Label {

    private final CellPosition cellPosition;

    public GuiCells(CellPosition cellPosition, Image image) {
        this.cellPosition = cellPosition;
        setColorToCell();
        setImage(image);
        setMinSize(75, 75);
        setMaxSize(75, 75);
    }

    public void setImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        this.setGraphic(imageView);
    }

    public CellPosition getPosition() {
        return cellPosition;
    }

    public void cellsFunctional(Field field) {
        setOnDragDetected(mouseEvent -> shapeDraggingFound(mouseEvent, field));
        setOnDragOver(this::draggingFigure);
        setOnDragDropped(dragEvent -> thePositionWhenDraggingTheShape(dragEvent, field));
        setOnDragDone(dragEvent -> dragged(dragEvent, field));
        setOnMouseEntered(e -> MouseOnFigure(field));
        setOnMouseExited(e -> mouseOnExited(field));
    }

    private void MouseOnFigure(Field field) {
        Figure figure = field.getPiece(cellPosition);
        if (figure != null && figure.getColor() == field.getCurr()) {
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : figure.countingCorrectPosition(field.getBoard())) {
                    if (cell.getCellPosition().equals(gc.getPosition())) {
                        if (cell.getFigure() != null) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void mouseOnExited(Field field) {
        Figure figure = field.getPiece(cellPosition);
        if (figure != null) {
            LinkedList<Cell> helpLink = figure.countingCorrectPosition(field.getBoard());
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getCellPosition().equals(gc.getPosition())) {
                        gc.setColorToCell();
                    }
                }
            }
        }
    }

    //Поиск перетаскивание фигуры
    private void shapeDraggingFound(MouseEvent e, Field field) {
        Figure figure = field.getPiece(cellPosition);
        if (figure != null && figure.getColor() == field.getCurr()) {
            LinkedList<Cell> helpLink = figure.countingCorrectPosition(field.getBoard());
            if (helpLink.size() > 0) {
                Dragboard db = startDragAndDrop(TransferMode.MOVE);
                db.setDragView(figure.getImage());
                ClipboardContent content = new ClipboardContent();
                content.put(Figure.CHESS_PIECE, figure);
                db.setContent(content);
                for (GuiCells gc : field.getCells()) {
                    for (Cell cell : helpLink) {
                        if (cell.getCellPosition().equals(gc.getPosition())) {
                        }
                    }
                }
            }
        }
        e.consume();
    }



    //перетаскивание фигуры
    private void draggingFigure(DragEvent e) {
        if (e.getDragboard().hasContent(Figure.CHESS_PIECE)) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
    }


    //положение фигуры при перетаскивании
    private void thePositionWhenDraggingTheShape(DragEvent e, Field field) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Figure.CHESS_PIECE)) {
            Figure figure = (Figure) db.getContent(Figure.CHESS_PIECE);
            figure = field.getPiece(figure.getCellPosition());
            LinkedList<Cell> helpLink = figure.countingCorrectPosition(field.getBoard());
            if (helpLink.size() > 0 && figure.countingCorrectPosition(field.getBoard()).contains(field.getBoard().getCell(this.cellPosition))) {
                setColorToCell();
                for (Cell cell : helpLink) {
                    if (cell.getCellPosition().equals(this.cellPosition)) {
                        setImage(figure.getImage());
                        for (GuiCells gc : field.getCells()) {
                            if (gc.cellPosition.equals(figure.getCellPosition())) {
                                gc.setGraphic(null);
                            }
                        }
                    }
                    if (cell.getCellPosition().equals(this.getPosition())) {
                        figure.move(field.getBoard(), cell);
                    }
                }
                field.gameTest();
            }
        }
        e.consume();
    }

    //совершено перетаскивание
    private void dragged(DragEvent e, Field field) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Figure.CHESS_PIECE)) {
            Figure figure = (Figure) db.getContent(Figure.CHESS_PIECE);
            figure.setCellPosition(field.getBoard().getCell(figure.getCellPosition()).getCellPosition());
            LinkedList<Cell> helpLink = figure.countingCorrectPosition(field.getBoard());
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getCellPosition().equals(gc.getPosition())) {
                        gc.setColorToCell();
                    }
                }
            }
        }
        e.consume();
    }

    private void setColorToCell() {
        if (getColor() == Color.WHITE) {
            setStyle("-fx-background-color: rgb(255, 255, 128);");
        } else {
            setStyle("-fx-background-color: rgb(150, 40, 10);");
        }
    }

    private Color getColor() {
        if ((cellPosition.getRow() + cellPosition.getColumn()) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
