package ticTacToe.control;
import java.util.*;

import ticTacToe.model.Mark;
import ticTacToe.model.table.TableModel;

public class LearningVirtualPlayer implements VirtualPlayer {
    private final TableModel tableModel;
    private final Mark mark;
    private final Map<String, double[]> qTable = new HashMap<>();
    private final double learningRate = 0.1;
    private final double discountFactor = 0.9;
    private final double explorationRate = 0.2;

    // Memória da partida atual
    private final List<Step> steps = new ArrayList<>();

    public LearningVirtualPlayer(TableModel tableModel, Mark mark) {
        this.tableModel = tableModel;
        this.mark = mark;
    }

    @Override
    public void play() {
        String state = getState();
        List<Integer> availableMoves = getAvailableMoves();

        qTable.putIfAbsent(state, new double[9]);

        int move;
        if (Math.random() < explorationRate) {
            move = availableMoves.get(new Random().nextInt(availableMoves.size()));
        } else {
            move = getBestMove(state, availableMoves);
        }

        int row = move / 3;
        int col = move % 3;
        tableModel.setMark(row, col, mark);

        // Registrar a jogada
        steps.add(new Step(state, move));
    }

    public void trainAtGameEnd(double finalReward) {
        // Treina em ordem reversa
        for (int i = steps.size() - 1; i >= 0; i--) {
            Step step = steps.get(i);
            String state = step.state;
            int action = step.action;

            String nextState = getState();
            double[] qValues = qTable.getOrDefault(state, new double[9]);
            qTable.putIfAbsent(nextState, new double[9]);

            double futureMax = Arrays.stream(qTable.get(nextState)).max().orElse(0);
            qValues[action] += learningRate * (finalReward + discountFactor * futureMax - qValues[action]);

            qTable.put(state, qValues);

            // Nas jogadas anteriores a recompensa vai diminuindo
            finalReward *= discountFactor;
        }

        steps.clear(); // Limpa para próxima partida
    }

    private String getState() {
        StringBuilder sb = new StringBuilder();
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                sb.append(tableModel.getMark(lin, col).name().charAt(0));
            }
        }
        return sb.toString();
    }

    private List<Integer> getAvailableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (tableModel.getMark(i / 3, i % 3) == Mark.BLANK)
                moves.add(i);
        }
        return moves;
    }

    private int getBestMove(String state, List<Integer> availableMoves) {
        double[] qValues = qTable.get(state);
        int bestMove = availableMoves.get(0);
        double bestValue = qValues[bestMove];

        for (int move : availableMoves) {
            if (qValues[move] > bestValue) {
                bestValue = qValues[move];
                bestMove = move;
            }
        }
        return bestMove;
    }

    private static class Step {
        String state;
        int action;

        Step(String state, int action) {
            this.state = state;
            this.action = action;
        }
    }
}