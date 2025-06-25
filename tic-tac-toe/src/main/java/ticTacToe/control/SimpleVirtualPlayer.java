package ticTacToe.control;

import ticTacToe.model.Mark;
import ticTacToe.model.table.TableModel;
import java.util.*;

public class SimpleVirtualPlayer implements VirtualPlayer {
    private final TableModel tableModel;
    private final Mark mark;
    private final Mark enemyMark;

    public SimpleVirtualPlayer(TableModel tableModel, Mark mark) {
        this.tableModel = tableModel;
        this.mark = mark;
        this.enemyMark = (mark == Mark.O) ? Mark.X : Mark.O;
    }

    @Override
    public void play() {
        if (playToWin())
            return;
        if (blockEnemyWin())
            return;
        if (playCenter())
            return;
        if (playCorner())
            return;
        playEdge(); // última opção
    }

    // 1. Verifica se pode vencer
    private boolean playToWin() {
        return tryCompleteLine(mark);
    }

    // 2. Verifica se precisa bloquear o inimigo
    private boolean blockEnemyWin() {
        return tryCompleteLine(enemyMark);
    }

    private boolean tryCompleteLine(Mark testMark) {
        // Tenta todas as 9 casas
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                if (tableModel.getMark(lin, col) == Mark.BLANK) {
                    tableModel.setMark(lin, col, testMark);
                    boolean win = hasWinner(testMark);
                    tableModel.setMark(lin, col, Mark.BLANK);
                    if (win) {
                        tableModel.setMark(lin, col, mark);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasWinner(Mark m) {
        // Linhas
        for (int i = 0; i < 3; i++)
            if (checkLine(m, i, 0, i, 1, i, 2)) return true;
        // Colunas
        for (int i = 0; i < 3; i++)
            if (checkLine(m, 0, i, 1, i, 2, i)) return true;
        // Diagonais
        return checkLine(m, 0, 0, 1, 1, 2, 2) || checkLine(m, 0, 2, 1, 1, 2, 0);
    }

    private boolean checkLine(Mark m, int l1, int c1, int l2, int c2, int l3, int c3) {
        return tableModel.getMark(l1, c1) == m &&
               tableModel.getMark(l2, c2) == m &&
               tableModel.getMark(l3, c3) == m;
    }

    // 3. Centro
    private boolean playCenter() {
        if (tableModel.getMark(1, 1) == Mark.BLANK) {
            tableModel.setMark(1, 1, mark);
            return true;
        }
        return false;
    }

    // 4. Canto (em ordem: topo esquerdo, topo direito, baixo esquerdo, baixo direito)
    private boolean playCorner() {
        int[][] corners = { {0,0}, {0,2}, {2,0}, {2,2} };
        for (int[] pos : corners) {
            if (tableModel.getMark(pos[0], pos[1]) == Mark.BLANK) {
                tableModel.setMark(pos[0], pos[1], mark);
                return true;
            }
        }
        return false;
    }

    // 5. Bordas
    private void playEdge() {
        int[][] edges = { {0,1}, {1,0}, {1,2}, {2,1} };
        for (int[] pos : edges) {
            if (tableModel.getMark(pos[0], pos[1]) == Mark.BLANK) {
                tableModel.setMark(pos[0], pos[1], mark);
                return;
            }
        }
    }
}