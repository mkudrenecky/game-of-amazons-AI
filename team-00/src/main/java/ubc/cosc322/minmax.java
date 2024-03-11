package ubc.cosc322;
import java.util.*;

public class MinMax {
    public int evaluation;
    public Action bestAction;

    public MinMax(int evaluation, Action bestAction) {
        this.evaluation = evaluation;
        this.bestAction = bestAction;
    }

    public static Action findBestAction(Board board, int depth, int player) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int maximizingPlayer = player;

        MinMax result = minMaxSearch(board, depth, alpha, beta, maximizingPlayer, player);

        Action bestAction = result.bestAction;
        
        return bestAction;
    }

    private static MinMax minMaxSearch(Board board, int depth, int alpha, int beta, int maximizingPlayer, int currentPlayer) {
        List<Action> legalActions = ActionFactory.getActions(board, currentPlayer);
        if (depth == 0 || legalActions.isEmpty()) {
            return new MinMax (evaluate(board, maximizingPlayer), null);
        }

        Action bestAction = null;

        if (currentPlayer == maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Action action : legalActions) {
                Board nextBoard = new Board(board, action);
                // System.out.println(nextBoard.boardToString());
                // System.out.println(board.boardToString());
                int eval = minMaxSearch(nextBoard, depth - 1, alpha, beta, maximizingPlayer, getOpponent(currentPlayer)).evaluation;
                if (eval > maxEval) {
                    maxEval = eval;
                    bestAction = action;
                }
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return new MinMax(maxEval, bestAction);
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Action action : legalActions) {
                Board nextBoard = new Board(board, action);
                // System.out.println(nextBoard.boardToString());
                // System.out.println(board.boardToString());
                int eval = minMaxSearch(nextBoard, depth - 1, alpha, beta, maximizingPlayer, getOpponent(currentPlayer)).evaluation;
                if (eval < minEval) {
                    minEval = eval;
                    bestAction = action;
                }
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return new MinMax(minEval, bestAction);
        }
    }

    private static int evaluate(Board board, int player){
        
        int mobilityScore = ActionFactory.getActions(board, player).size() - ActionFactory.getActions(board, getOpponent(player)).size();
        // System.out.println("Mobility score for player " + player + ": " + mobilityScore);
        return mobilityScore;
    }

    private static int getOpponent(int player) {
        return player == 1 ? 2 : 1;
    }
}
    /*MINIMAX(s) =
UTILITY(s, MAX) if IS-TERMINAL(s)
maxa∈Actions(s) MINIMAX(RESULT(s, a)) if TO-MOVE(s)= MAX
mina∈Actions(s) MINIMAX(RESULT(s, a)) if TO-MOVE(s)= MIN 

function MINIMAX-SEARCH(game, state) returns an action
player←game.TO-MOVE(state)
value, move←MAX-VALUE(game, state)
return move
function MAX-VALUE(game, state) returns a (utility, move) pair
if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
v←−∞
for each a in game.ACTIONS(state) do
v2, a2←MIN-VALUE(game, game.RESULT(state, a))
if v2 > v then
v, move←v2, a
return v, move
function MIN-VALUE(game, state) returns a (utility, move) pair
if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
v←+∞
for each a in game.ACTIONS(state) do
v2, a2←MAX-VALUE(game, game.RESULT(state, a))
if v2 < v then
v, move←v2, a
return v, move
*/
    
