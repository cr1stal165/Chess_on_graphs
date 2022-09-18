package chess.pieces;

import chess.board.Board;
import chess.board.CellPosition;
import chess.board.Figure;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class King extends Figure {

    public King(Color color, CellPosition pos) {
        super(color, pos, "K");
    }

    @Override
    public ArrayList<CellPosition> moveFigureToAnotherPosition(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        char column = this.getCellPosition().getColumn();
        int row = this.getCellPosition().getRow();
//        if (new CellPosition(column, row + 1).posOnDesk() && board.getCell(new CellPosition(column, row + 1)).getPiece().getColor() != this.getColor()) {
//            correctPos.add(new CellPosition(column, row - 1));
//        }
        for (int i = row - 1; i <= row + 1; i++) {
            column--;
            if (new CellPosition(column, i).positionOnBoard()) {
                if (board.getCell(new CellPosition(column, i)).getFigure() == null) {
                    correctPos.add(new CellPosition(column, i));
                } else if (board.getCell(new CellPosition(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new CellPosition(column, i));
                }
            }
            column++;
            if (new CellPosition(column, i).positionOnBoard()) {
                if (board.getCell(new CellPosition(column, i)).getFigure() == null) {
                    correctPos.add(new CellPosition(column, i));
                } else if (board.getCell(new CellPosition(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new CellPosition(column, i));
                }
            }
            column++;
            if (new CellPosition(column, i).positionOnBoard()) {
                if (board.getCell(new CellPosition(column, i)).getFigure() == null) {
                    correctPos.add(new CellPosition(column, i));
                } else if (board.getCell(new CellPosition(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new CellPosition(column, i));
                }
            }
            column = this.getCellPosition().getColumn();
        }
        return correctPos;
    }

    @Override
    public ArrayList<CellPosition> checkPositionBetweenKing(Board board) {
        return new ArrayList<>();
    }

}
