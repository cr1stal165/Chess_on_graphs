package chess.functional;

import chess.board.CellPosition;
import chess.board.Figure;
import chess.pieces.*;
import chess.game.GuiCells;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import chess.board.Board;
import chess.board.Cell;
import chess.game.GUI;
import java.util.ArrayList;

public class Field {

    private final GridPane gridPane;
    private Board board;
    private static final Logger logger = LoggerFactory.getLogger(Field.class);
    private final ArrayList<GuiCells> cells;
    private Color curr = Color.WHITE;
    GUI GUI = new GUI();
    Match match = new Match();

    public Field() {
        board = new Board();
        gridPane = new GridPane();
        cells = new ArrayList<>();
        installationFigures();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GuiCells cell = GUI.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                gridPane.add(cell, col, row);
            }
        }
    }

    public void gameTest() {
        pawnReplace();
        curr = curr == Color.BLACK ? Color.WHITE : Color.BLACK;
        if (board.getKingCell(curr).getFigure().countingCorrectPosition(this.getBoard()).size() == 0) {
            for (Cell cell : board.getGraph()) {
                if (cell.getFigure() != null && cell.getFigure().countingCorrectPosition(board).size() > 0 && cell.getFigure().getColor() == curr) {
                    return;
                }
            }
            match.finishTheGame(curr);
        }
    }

    public void pawnReplace() {
        Cell cell = board.canPawnTakeCells();
        if (cell != null) {
            GUI.showFigures(cell.getFigure().getColor(), cell.getCellPosition(), this);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        board.refreshGraph();
        cells.clear();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GuiCells cell = GUI.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                gridPane.add(cell, col, row);
            }
        }
    }

    public Color getCurr() {
        return curr;
    }

    private void installationFigures() {
        for (Cell cell : board.getGraph()) {
            if (cell.getCellPosition().equals(new CellPosition('A', 8)) || cell.getCellPosition().equals(new CellPosition('H', 8)) ||
                    cell.getCellPosition().equals(new CellPosition('A', 1)) || cell.getCellPosition().equals(new CellPosition('H', 1))) {
                cell.setFigure(new Rook((cell.getCellPosition().equals(new CellPosition('A', 8)) || cell.getCellPosition().equals(new CellPosition('H', 8))) ? Color.BLACK : Color.WHITE, cell.getCellPosition()));
            }
            if (cell.getCellPosition().equals(new CellPosition('B', 8)) || cell.getCellPosition().equals(new CellPosition('G', 8)) ||
                    cell.getCellPosition().equals(new CellPosition('B', 1)) || cell.getCellPosition().equals(new CellPosition('G', 1))) {
                cell.setFigure(new Knight((cell.getCellPosition().equals(new CellPosition('B', 8)) || cell.getCellPosition().equals(new CellPosition('G', 8))) ? Color.BLACK : Color.WHITE, cell.getCellPosition()));
                if (cell.getCellPosition().getRow() == 1) {
                    char ch = cell.getCellPosition().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 3)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 3)));
                } else {
                    char ch = cell.getCellPosition().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 6)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 3)));
                }
            }
            if (cell.getCellPosition().equals(new CellPosition('C', 8)) || cell.getCellPosition().equals(new CellPosition('F', 8)) ||
                    cell.getCellPosition().equals(new CellPosition('C', 1)) || cell.getCellPosition().equals(new CellPosition('F', 1))) {
                cell.setFigure(new Bishop((cell.getCellPosition().equals(new CellPosition('C', 8)) || cell.getCellPosition().equals(new CellPosition('F', 8))) ? Color.BLACK : Color.WHITE, cell.getCellPosition()));
            }
            if (cell.getCellPosition().equals(new CellPosition('D', 8)) || cell.getCellPosition().equals(new CellPosition('D', 1))) {
                cell.setFigure(new Queen(cell.getCellPosition().equals(new CellPosition('D', 8)) ? Color.BLACK : Color.WHITE, cell.getCellPosition()));
            }
            if (cell.getCellPosition().equals(new CellPosition('E', 8)) || cell.getCellPosition().equals(new CellPosition('E', 1))) {
                cell.setFigure(new King(cell.getCellPosition().equals(new CellPosition('E', 8)) ? Color.BLACK : Color.WHITE, cell.getCellPosition()));
            }
            if (cell.getCellPosition().getRow() == 7 || cell.getCellPosition().getRow() == 2) {
                cell.setFigure(new Pawn(cell.getCellPosition().getRow() == 7 ? Color.BLACK : Color.WHITE, cell.getCellPosition()));

                char ch = cell.getCellPosition().getColumn();
                if (cell.getCellPosition().getRow() == 7) {
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 7 - 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 7 - 2)));
                } else {
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 2 + 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new CellPosition(ch, 2 + 2)));
                }
            }
            try {
                logger.info("Фигура " + cell.getFigure().getName() + " была поставлена на клетку: " +  cell.getCellPosition().getColumn() + cell.getCellPosition().getRow());
            } catch (Exception e) {
                logger.info("Клетка " + cell.getCellPosition() +  " пуста");
            }
        }
    }

    public ArrayList<GuiCells> getCells() {
        return cells;
    }

    public Board getBoard() {
        return board;
    }

    public Figure getPiece(CellPosition cellPosition) {
        return this.board.getCell(cellPosition).getFigure();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    @Override
    public String toString() {
        return "Field{" +
                ", board=" + board +
                '}';
    }
}
