package chess.board;

import javafx.scene.image.Image;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Figure implements Serializable {

    public transient static final DataFormat CHESS_PIECE = new DataFormat("chess.piece");
    private final transient static Map<String, Image> imageCache = new HashMap<>();
    private CellPosition cellPosition;
    private final String name;
    private final String imageFileName;
    private final transient Color color;

    protected Figure(Color color, CellPosition cellPosition, String name) {
        this.color = color;
        this.cellPosition = cellPosition;
        this.name = name;
        imageFileName = "File:images/" + (color.equals(Color.BLACK)
                ? "B"
                : "W") + name + ".png";
        if (!imageCache.containsKey(imageFileName)) {
            imageCache.put(imageFileName, new Image(imageFileName));
        }
    }

    public Image getImage() {
        return imageCache.get(imageFileName);
    }

    public Color getColor() {
        return color;
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    public String getName() {
        return name;
    }

    public void setCellPosition(CellPosition cellPosition) {
        this.cellPosition = cellPosition;
    }

    public void move(Board board, Cell cell) {
        board.getCell(getCellPosition()).setFigure(null);
        this.setCellPosition(cell.getCellPosition());
        cell.setFigure(this);
        board.refreshGraph();
    }


    public LinkedList<Cell> countingCorrectPosition(Board board) {
        for (Cell current : board.getGraph()) {
            if (current.getCellPosition() == this.getCellPosition()) {
                return board.getGraph().allEdges(current);
            }
        }
        return new LinkedList<>();
    }

    public abstract ArrayList<CellPosition> moveFigureToAnotherPosition(Board board);

    public abstract ArrayList<CellPosition> checkPositionBetweenKing(Board board);

}
