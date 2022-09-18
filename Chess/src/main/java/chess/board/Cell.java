package chess.board;

public class Cell {

    private final CellPosition cellPosition;
    private Figure figure;

    public Cell(CellPosition cellPosition, Figure figure) {
        this.cellPosition = cellPosition;
        this.figure = figure;
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return cellPosition.toString() + ": " + (figure != null ? (figure.getName() + "; ") : "null; ");
    }
}
