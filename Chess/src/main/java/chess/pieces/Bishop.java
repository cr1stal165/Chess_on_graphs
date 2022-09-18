package chess.pieces;

import chess.board.Board;
import chess.board.CellPosition;
import chess.board.Figure;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Bishop extends Figure {

    public Bishop(Color color, CellPosition pos) {
        super(color, pos, "B");
    }

    @Override
    public ArrayList<CellPosition> moveFigureToAnotherPosition(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        char column = this.getCellPosition().getColumn();
        column--;
        int row = this.getCellPosition().getRow();
        row++;
        while (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(column, row)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row++;
                    if (new CellPosition(column, row).positionOnBoard()) {
                        correctPos.add(new CellPosition(column, row));
                    }
                    break;
                }
            }
            column--;
            row++;
        }
        column = this.getCellPosition().getColumn();
        column++;
        row = this.getCellPosition().getRow();
        row--;
        while (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(column, row)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row--;
                    if (new CellPosition(column, row).positionOnBoard()) {
                        correctPos.add(new CellPosition(column, row));
                    }
                    break;
                }
            }
            column++;
            row--;
        }
        column = this.getCellPosition().getColumn();
        column++;
        row = this.getCellPosition().getRow();
        row++;
        while (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(column, row)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row++;
                    if (new CellPosition(column, row).positionOnBoard()) {
                        correctPos.add(new CellPosition(column, row));
                    }
                    break;
                }
            }
            column++;
            row++;
        }
        column = this.getCellPosition().getColumn();
        column--;
        row = this.getCellPosition().getRow();
        row--;
        while (new CellPosition(column, row).positionOnBoard()) {
            correctPos.add(new CellPosition(column, row));
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(column, row)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row--;
                    if (new CellPosition(column, row).positionOnBoard()) {
                        correctPos.add(new CellPosition(column, row));
                    }
                    break;
                }
            }
            column--;
            row--;
        }
        return correctPos;
    }

    @Override
    public ArrayList<CellPosition> checkPositionBetweenKing(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        correctPos.add(this.getCellPosition());
        char column = this.getCellPosition().getColumn();
        column--;
        int row = this.getCellPosition().getRow();
        row++;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column--;
            row++;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        column = this.getCellPosition().getColumn();
        column++;
        row = this.getCellPosition().getRow();
        row--;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column++;
            row--;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        column = this.getCellPosition().getColumn();
        column++;
        row = this.getCellPosition().getRow();
        row++;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column++;
            row++;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        column = this.getCellPosition().getColumn();
        column--;
        row = this.getCellPosition().getRow();
        row--;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column--;
            row--;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        return correctPos;
    }
}
