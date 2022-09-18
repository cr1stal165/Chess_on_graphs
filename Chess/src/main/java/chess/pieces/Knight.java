package chess.pieces;

import chess.board.CellPosition;
import chess.board.Figure;
import chess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Knight extends Figure {

    public Knight(Color color, CellPosition pos) {
        super(color, pos, "Knt");
    }

    @Override
    public ArrayList<CellPosition> moveFigureToAnotherPosition(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        char column = this.getCellPosition().getColumn();
        int row = this.getCellPosition().getRow();
        row += 1;
        column += 2;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        row -= 2;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        row += 2;
        column -= 4;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        row -= 2;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        column++;
        row += 3;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        row -= 4;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        column += 2;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        row += 4;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        return correctPos;
    }

    @Override
    public ArrayList<CellPosition> checkPositionBetweenKing(Board board) {
        return new ArrayList<>();
    }

}
