package ubc.cosc322;
import java.util.*;

public class Negamax {
    public int evaluation;
    public Action bestAction;
    private int nodeCount = 0;

    public Negamax(int evaluation, Action bestAction) {
        this.evaluation = evaluation;
        this.bestAction = bestAction;
    }

    public static Action findBestAction(Board board, int depth, int player, int heuristic, long startTime, long timeLimit) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        Negamax result = negamaxSearch(board, depth, alpha, beta, player, heuristic, startTime, timeLimit);

        Action bestAction = result.bestAction;

        return bestAction;
    }

    private static Negamax negamaxSearch(Board board, int depth, int alpha, int beta, int player, int heuristic, long startTime,long timeLimit) {
        List<Action> legalActions = ActionFactory.getActions(board, player);
        if (depth == 0 || legalActions.isEmpty()) {
            return new Negamax(evaluate(board, player, heuristic), null);
        }

        Action bestAction = null;
        int maxEval = Integer.MIN_VALUE;

        for (Action action : legalActions) {
            Board nextBoard = new Board(board, action);
            if (System.currentTimeMillis() - startTime >= timeLimit) {
                System.out.println("Time's up! Search interrupted.");
                return new Negamax(maxEval, bestAction); // Return the best action found so far
            }
            int eval = -negamaxSearch(nextBoard, depth - 1, -beta, -alpha, getOpponent(player), heuristic, startTime, timeLimit).evaluation;
            if (eval > maxEval) {
                maxEval = eval;
                bestAction = action;
            }
            alpha = Math.max(alpha, eval);
            if (beta <= alpha) {
                break;
            }
        }
        return new Negamax(maxEval, bestAction);
    }

    private static int evaluate(Board board, int player, int heuristic) {
        switch (heuristic) {
            case 1:
                return mobilityHeuristic(board, player);
            case 2:
                return territoryHeuristic(board, player);
            default:
                return 0; // Default evaluation if heuristic not specified
        }
    }

    private static int mobilityHeuristic(Board board, int player) {
        int mobilityScore = ActionFactory.getActions(board, player).size() - ActionFactory.getActions(board, getOpponent(player)).size();
        return mobilityScore;
    }

    private static int territoryHeuristic(Board board, int player) {
        int playerTerritory = 0;
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                ArrayList<Action> playerActions = ActionFactory.getActions(board, player);
                ArrayList<Action> opponentActions = ActionFactory.getActions(board, getOpponent(player));
                int playerSquare = 0;
                int opponentSquare = 0;
                for (Action action : playerActions) {
                    if (action.getQueenMove().getEndCol() == i && action.getQueenMove().getEndRow() == j) {
                        playerSquare++;
                    }
                }
                for (Action action : opponentActions) {
                    if (action.getQueenMove().getEndCol() == i && action.getQueenMove().getEndRow() == j) {
                        opponentSquare++;
                    }
                }
                int squareOwner = playerSquare - opponentSquare;
                if (squareOwner < 0)
                    playerTerritory--;
                if (squareOwner > 0)
                    playerTerritory++;
            }
        }
        return playerTerritory;
    }

    private static int getOpponent(int player) {
        return player == 1 ? 2 : 1;
    }
}
