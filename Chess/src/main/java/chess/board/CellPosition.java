package chess.board;

import java.io.Serializable;
import java.util.Objects;

public class CellPosition implements Serializable {

    private final char column;
    private final int row;

    public CellPosition(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean positionOnBoard() {
        return this.getRow() <= 8 && this.getRow() >= 1 && this.getColumn() >= 'A' && this.getColumn() <= 'H';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition cellPosition = (CellPosition) o;
        return column == cellPosition.column && row == cellPosition.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return (this.column) + Integer.toString(this.row);
    }
}
