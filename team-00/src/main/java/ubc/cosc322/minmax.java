package ubc.cosc322;
import java.util.*;

public class MinMax {
    public int evaluation;
    public Action bestAction;
    private int nodeCount = 0;

    public MinMax(int evaluation, Action bestAction) {
        this.evaluation = evaluation;
        this.bestAction = bestAction;
    }

    public static Action findBestAction(Board board, int depth, int player, int heuristic, long startTime, long timeLimit) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int maximizingPlayer = player;

        MinMax result = minMaxSearch(board, depth, alpha, beta, maximizingPlayer, player, heuristic, startTime, timeLimit);

        Action bestAction = result.bestAction;


        return bestAction;
    }

    private static MinMax minMaxSearch(Board board, int depth, int alpha, int beta, int maximizingPlayer, int currentPlayer, int heuristic, long startTime, long timeLimit) {
        // setNodeCount(getNodeCount()++);
        List<Action> legalActions = ActionFactory.getActions(board, currentPlayer);
        if (depth == 0 || legalActions.isEmpty()) {
            return new MinMax (evaluate(board, maximizingPlayer, heuristic), null);
        }

        Action bestAction = null;
        
        if (currentPlayer == maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Action action : legalActions) {
                if (System.currentTimeMillis() - startTime >= timeLimit) {
                    System.out.println("Time's up! Search interrupted.");
                    return new MinMax(maxEval, bestAction); // Return the best action found so far
                }
                Board nextBoard = new Board(board, action);
                int eval = minMaxSearch(nextBoard, depth - 1, alpha, beta, maximizingPlayer, getOpponent(currentPlayer), heuristic, startTime, timeLimit).evaluation;
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
                if (System.currentTimeMillis() - startTime >= timeLimit) {
                    System.out.println("Time's up! Search interrupted.");
                    return new MinMax(minEval, bestAction); // Return the best action found so far
                }
                Board nextBoard = new Board(board, action);
                int eval = minMaxSearch(nextBoard, depth - 1, alpha, beta, maximizingPlayer, getOpponent(currentPlayer), heuristic, startTime, timeLimit).evaluation;
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

    private static int evaluate(Board board, int player, int heuristic){
        int evaluation = Integer.MIN_VALUE;
        switch(heuristic){
            // case 0:
            //     evaluation = randomHeuristic(board, (byte) player);
            //     break;
            case 1:
                evaluation = mobilityHeuristic(board, player);
                break;
            case 2:
                evaluation = territoryHeuristic(board,  player);
                break;
            case 3:
                //mobility-territory heuristic
                break;
        }
        return evaluation;
    }
    
    private static int mobilityHeuristic(Board board, int player){
        int mobilityScore = ActionFactory.getActions(board, player).size() - ActionFactory.getActions(board, getOpponent(player)).size();
        return mobilityScore;
    }

    private static int territoryHeuristic(Board board, int player){
        ArrayList<Action> playerActions =  ActionFactory.getActions(board, player);
        ArrayList<Action> opponentActions =  ActionFactory.getActions(board, getOpponent(player));
        int playerTerritory = 0;
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); j++){
                
                // actions.stream().collect(Collectors.toMap(Action::actions.getQueenMove().getEndCol(), null));
                int playerSquare = 0;
                int opponentSquare = 0;
                for(Action action : playerActions){
                    if (action.getQueenMove().getEndCol() == i && action.getQueenMove().getEndRow() == j){
                        playerSquare++;
                    }
                }
                for(Action action : opponentActions){
                    if (action.getQueenMove().getEndCol() == i && action.getQueenMove().getEndRow() == j){
                        opponentSquare++;
                }
                int squareOwner = playerSquare - opponentSquare;
                if(squareOwner < 0)
                    playerTerritory--;
                if(squareOwner > 0)
                    playerTerritory++;
            }
        }
    }
        return playerTerritory;
    }

    private static int getOpponent(int player) {
        return player == 1 ? 2 : 1;
    }

    private int getNodeCount(){
        return this.nodeCount;
    }
    private void setNodeCount(int nodeCount){
        this.nodeCount = nodeCount;
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
    
