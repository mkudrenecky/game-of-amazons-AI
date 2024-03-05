package ubc.cosc322;
import java.util.ArrayList;
import java.util.List;

public class ActionFactory {


    public static ArrayList<Action> getActions(Board board, int player){
        // System.out.println("Generating actions.. for: " + player);
        ArrayList<Action> actions = new ArrayList<>();

        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getPieceAt(i,j) != 0 && board.getPieceAt(i, j) == player){
                    List<QueenMove> queenMoves = generateQueenMoves(board, i, j);
                    for (QueenMove queenMove : queenMoves){
                        List<ArrowShot> arrowMoves = generateArrowMoves(board, queenMove.getEndRow(), queenMove.getEndCol());
                        for (ArrowShot arrowShot : arrowMoves){
                            actions.add(new Action(queenMove, arrowShot));
                        }
                    
                    }
                }
            }
        }
        return actions;
    }

    private static List<QueenMove> generateQueenMoves(Board board, int startRow, int startCol){
        List<QueenMove> queenMoves = new ArrayList<>();
        // move up
        for (int row = startRow+1; row < board.getBoardSize(); row++){
            if (!addQueenMoveIfValid(startRow, startCol, row, startCol, board, queenMoves)){
                break;
            }
        }

        // move left
        for (int col = startCol-1; col >= 0; col--){
            if (!addQueenMoveIfValid(startRow, startCol, startRow, col, board, queenMoves)){
                break;
            }
        }

        // move right
        for (int col = startCol+1; col < board.getBoardSize(); col++){
            if (!addQueenMoveIfValid(startRow, startCol, startRow, col, board, queenMoves)){
                break;
            }
        }

        // move down
        for (int row = startRow-1; row >= 0; row--){
            if (!addQueenMoveIfValid(startRow, startCol, row, startCol, board, queenMoves)){
                break;
            }
        }

        // move up-right

        // move up-left

        // move down-left

        // move down-right
        return queenMoves;
    }

    private static List<ArrowShot> generateArrowMoves(Board board, int startRow, int startCol){
        List<ArrowShot> arrowMoves = new ArrayList<>();
        // arrow will have same logic as queen move 

        //shoot up
        for (int row = startRow+1; row < board.getBoardSize(); row++){
            if (!addArrowMoveIfValid(startRow, startCol, row, startCol, board, arrowMoves)){
                break;
            }
        }

        // shoot down
        for (int row = startRow-1; row >= 0; row--){
            if (!addArrowMoveIfValid(startRow, startCol, row, startCol, board, arrowMoves)){
                break;
            }
        }

        // shoot right
        for (int col = startCol+1; col < board.getBoardSize(); col++){
            if (!addArrowMoveIfValid(startRow, startCol, startRow, col, board, arrowMoves)){
                break;
            }
        }

        // shoot left
        for (int col = startCol-1; col >= 0; col--){
            if (!addArrowMoveIfValid(startRow, startCol, startRow, col, board, arrowMoves)){
                break;
            }
        }

        // shoot up-right

        // shoot up-left

        // shoot down-right

        // shoot down-left
        return arrowMoves;
    }

    private static boolean addQueenMoveIfValid(int startRow, int startCol, int endRow, int endCol, Board board, List<QueenMove> queenMoves){
        if (endRow >= board.getBoardSize() || endCol >= board.getBoardSize() || endRow < 0 || endCol < 0){
            return false;
        }
        if (board.getPieceAt(endRow, endCol) == 0){
            queenMoves.add(new QueenMove(startRow, startCol, endRow, endCol));
            return true;
        }
        return false;
    }

    private static boolean addArrowMoveIfValid(int startRow, int startCol, int endRow, int endCol, Board board, List<ArrowShot> arrowMoves){
        if (endRow >= board.getBoardSize() || endCol >= board.getBoardSize() || endRow < 0 || endCol < 0){
            return false;
        }
        if (board.getPieceAt(endRow, endCol) == 0){
            arrowMoves.add(new ArrowShot(startRow, startCol, endRow, endCol));
            return true;
        }
        return false;
    }
}
