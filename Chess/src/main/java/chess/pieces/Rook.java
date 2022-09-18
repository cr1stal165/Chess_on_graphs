package chess.pieces;

import chess.board.CellPosition;
import chess.board.Figure;
import chess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Rook extends Figure {

    public Rook(Color color, CellPosition pos) {
        super(color, pos, "R");
    }

    @Override
    public ArrayList<CellPosition> moveFigureToAnotherPosition(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        char columns = this.getCellPosition().getColumn();
        int rows = this.getCellPosition().getRow();
        rows++;
        while (new CellPosition(columns, rows).positionOnBoard()) {
            correctPos.add(new CellPosition(columns, rows));
            if (board.getCell(new CellPosition(columns, rows)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(columns, rows)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(columns, rows)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    rows++;
                    if (new CellPosition(columns, rows).positionOnBoard()) {
                        correctPos.add(new CellPosition(columns, rows));
                    }
                    break;
                }
            }
            rows++;
        }
        rows = this.getCellPosition().getRow();
        rows--;
        while (new CellPosition(columns, rows).positionOnBoard()) {
            correctPos.add(new CellPosition(columns, rows));
            if (board.getCell(new CellPosition(columns, rows)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(columns, rows)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(columns, rows)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    rows--;
                    if (new CellPosition(columns, rows).positionOnBoard()) {
                        correctPos.add(new CellPosition(columns, rows));
                    }
                    break;
                }
            }
            rows--;
        }
        rows = this.getCellPosition().getRow();
        columns++;
        while (new CellPosition(columns, rows).positionOnBoard()) {
            correctPos.add(new CellPosition(columns, rows));
            if (board.getCell(new CellPosition(columns, rows)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(columns, rows)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(columns, rows)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    columns++;
                    if (new CellPosition(columns, rows).positionOnBoard()) {
                        correctPos.add(new CellPosition(columns, rows));
                    }
                    break;
                }
            }
            columns++;
        }
        columns = this.getCellPosition().getColumn();
        columns--;
        while (new CellPosition(columns, rows).positionOnBoard()) {
            correctPos.add(new CellPosition(columns, rows));
            if (board.getCell(new CellPosition(columns, rows)).getFigure() != null) {
                if (!(board.getCell(new CellPosition(columns, rows)).getFigure() instanceof King)
                        || board.getCell(new CellPosition(columns, rows)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    columns--;
                    if (new CellPosition(columns, rows).positionOnBoard()) {
                        correctPos.add(new CellPosition(columns, rows));
                    }
                    break;
                }
            }
            columns--;
        }
        return correctPos;
    }

    @Override
    public ArrayList<CellPosition> checkPositionBetweenKing(Board board) {
        ArrayList<CellPosition> correctPos = new ArrayList<>();
        correctPos.add(this.getCellPosition());
        char column = this.getCellPosition().getColumn();
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
            row++;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
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
            row--;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        row = this.getCellPosition().getRow();
        column++;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column++;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        column = this.getCellPosition().getColumn();
        column--;
        while (new CellPosition(column, row).positionOnBoard()) {
            if (board.getCell(new CellPosition(column, row)).getFigure() != null) {
                if (board.getCell(new CellPosition(column, row)).getFigure() instanceof King
                        && board.getCell(new CellPosition(column, row)).getFigure().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new CellPosition(column, row));
            column--;
        }
        correctPos.clear();
        correctPos.add(this.getCellPosition());
        return correctPos;
    }

}
