package chess.board;

import chess.functional.Field;
import chess.pieces.King;
import chess.pieces.Pawn;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private static final int SIZE_BOARD = 8;
    private final Graph<Cell> graph;
    private static final Logger logger = LoggerFactory.getLogger(Board.class);
    private Field field;

    public Board() {
        graph = new Graph<>();
        for (int row = 1; row <= SIZE_BOARD; row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                graph.addVertex(new Cell(new CellPosition(col, row), null));
            }
        }
        logger.info("Доска проинициализированна успешно!.");
    }

    public void refreshGraph() {
        try {
            for (Cell cell : graph) {
                if (cell.getFigure() == null) {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                } else {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                    for (CellPosition cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                        if (getCell(cellPosition).getFigure() != null) {
                            if (getCell(cellPosition).getFigure().getColor() != cell.getFigure().getColor()) {
                                graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                            }
                        } else {
                            if (cell.getFigure() instanceof Pawn) {
                                if (cellPosition.getColumn() == cell.getFigure().getCellPosition().getColumn()) {
                                    graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                                }
                            } else {
                                graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                            }
                        }
                    }
                }
            }
            refreshGraphForKing(getKingCell(Color.WHITE));
            refreshGraphForKing(getKingCell(Color.BLACK));
            ArrayList<Figure> attackingFigures = kingAttackingFigures(getKingCell(Color.WHITE));
            if (attackingFigures.size() == 1) {
                refreshGraphWhenKingIsAttacked(Color.WHITE, attackingFigures.get(0));
            }
            if (attackingFigures.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getFigure().getColor() != Color.WHITE && !(cell.getFigure() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
            attackingFigures = kingAttackingFigures(getKingCell(Color.BLACK));
            if (attackingFigures.size() == 1) {
                refreshGraphWhenKingIsAttacked(Color.BLACK, attackingFigures.get(0));
            }
            if (attackingFigures.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getFigure().getColor() != Color.BLACK && !(cell.getFigure() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cell canPawnTakeCells() {
        for (Cell cell : graph) {
            if (cell.getFigure() != null && cell.getFigure() instanceof Pawn) {
                if (cell.getCellPosition().getRow() == 1 && cell.getFigure().getColor() == Color.BLACK) {
                    return cell;
                }
                if (cell.getCellPosition().getRow() == 8 && cell.getFigure().getColor() == Color.WHITE) {
                    return cell;
                }
            }
        }
        return null;
    }

    public void refreshGraphWhenKingIsAttacked(Color color, Figure figure) {
        for (Cell cell : graph) {
            if (cell.getFigure() != null && cell.getFigure().getColor() == color) {
                if (!(cell.getFigure() instanceof King)) {
                    graph.removeEdges(cell);
                } else {
                    continue;
                }
                for (CellPosition cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                    if (figure.checkPositionBetweenKing(this).contains(cellPosition)) {
                        System.out.println(Arrays.toString(figure.checkPositionBetweenKing(this).toArray()));
                        if (cell.getFigure() instanceof Pawn) {
                            if (cell.getFigure().getCellPosition().getColumn() != cellPosition.getColumn()) {
                                if (cellPosition.getColumn() == figure.getCellPosition().getColumn()) {
                                    graph.createEdgeFromV1ToV2(cell, this.getCell(cellPosition));
                                }
                            } else {
                                graph.createEdgeFromV1ToV2(cell, this.getCell(cellPosition));
                            }
                        } else if (this.getCell(cellPosition).getFigure() == null || this.getCell(cellPosition).getFigure().getColor() != color) {
                            graph.createEdgeFromV1ToV2(cell, this.getCell(cellPosition));
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Figure> kingAttackingFigures(Cell kingCell) {
        ArrayList<Figure> attackingFigures = new ArrayList<>();
        for (Cell cell : graph) {
            if (graph.allEdges(cell).contains(kingCell)) {
                attackingFigures.add(cell.getFigure());
            }
        }
        return attackingFigures;
    }

    public void refreshGraphForKing(Cell current) {
        try {
            for (Cell cell : graph) {
                if (cell.getFigure() != null && cell.getFigure().getColor() != current.getFigure().getColor()) {
                    if (cell.getFigure() instanceof King) {
                        for (CellPosition cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                            if (current.getFigure().countingCorrectPosition(this).contains(this.getCell(cellPosition))) {
                                graph.removeEdge(current, this.getCell(cellPosition));
                            }
                        }
                    }
                    for (CellPosition cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                        if (pawnConditionToKing(cell, this.getCell(cellPosition), current)) {
                            if (current.getFigure().countingCorrectPosition(this).contains(this.getCell(cellPosition))) {
                                logger.info(cellPosition.toString());
                                graph.removeEdge(current, this.getCell(cellPosition));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private boolean pawnConditionToKing(Cell firstCell, Cell secondCell, Cell targetCell) {
        if (firstCell.getFigure() instanceof Pawn) {
            for (Cell help : targetCell.getFigure().countingCorrectPosition(this)) {
                if (!help.getCellPosition().equals(secondCell.getCellPosition())) {
                    continue;
                }
                char column = firstCell.getCellPosition().getColumn();
                column++;
                char column2 = firstCell.getCellPosition().getColumn();
                column2--;
                int row = firstCell.getFigure().getColor() == Color.WHITE ? firstCell.getCellPosition().getRow() + 1 : firstCell.getCellPosition().getRow() - 1;
                if (secondCell.getCellPosition().getRow() == row && (secondCell.getCellPosition().getColumn() == column2
                        || secondCell.getCellPosition().getColumn() == column)) {
                    return true;
                }
                return firstCell.getCellPosition().getColumn() != secondCell.getCellPosition().getColumn();
            }
        }
        return true;
    }

    public Cell getKingCell(Color color) {
        for (Cell cell : graph) {
            if (cell.getFigure() != null) {
                if (cell.getFigure() instanceof King && cell.getFigure().getColor() == color) {
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell getCell(CellPosition cellPosition) {
        for (Cell cell : graph) {
            if (cell.getCellPosition().equals(cellPosition)) {
                return cell;
            }
        }
        return null;
    }

    public int getSizeBoard() {
        return SIZE_BOARD;
    }

    public Graph<Cell> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "Board{" +
                "graph=" + graph.toString() +
                '}';
    }
}
