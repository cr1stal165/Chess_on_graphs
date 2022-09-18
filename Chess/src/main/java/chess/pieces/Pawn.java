package chess.pieces;

import chess.board.CellPosition;
import chess.board.Figure;
import chess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Pawn extends Figure {

    public Pawn(Color color, CellPosition pos) {
        super(color, pos, "P");
    }

    @Override
    public ArrayList<CellPosition> moveFigureToAnotherPosition(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        char column = this.getCellPosition().getColumn();
        int row = this.getCellPosition().getRow();
        if (row == 7 && getColor() == Color.BLACK) {
            if (board.getCell(new CellPosition(column, row - 1)).getFigure() == null) {
                correctPos.add(new CellPosition(column, row - 1));
                if (board.getCell(new CellPosition(column, row - 2)).getFigure() == null) {
                    correctPos.add(new CellPosition(column, row - 2));
                }
            }
        } else if (row == 2 && getColor() == Color.WHITE){
            if (board.getCell(new CellPosition(column, row + 1)).getFigure() == null) {
                correctPos.add(new CellPosition(column, row + 1));
                if (board.getCell(new CellPosition(column, row + 2)).getFigure() == null) {
                    correctPos.add(new CellPosition(column, row + 2));
                }
            }
        } else {
            row = this.getColor() == Color.WHITE ? row + 1 : row - 1;
            if (new CellPosition(column, row).positionOnBoard() && board.getCell(new CellPosition(column, row)).getFigure() == null) {
                correctPos.add(new CellPosition(column, row));
            }
        }
        column++;
        if (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
        }
        column -= 2;
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
