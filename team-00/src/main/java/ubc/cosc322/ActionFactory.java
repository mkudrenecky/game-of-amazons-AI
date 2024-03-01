package ubc.cosc322;
import java.util.ArrayList;
import java.util.List;

public class ActionFactory extends BaseActionFactory<Board>{


    public ArrayList<Action> getActions(Board board, int player){
        ArrayList<Action> actions = new ArrayList<>();

        for (int i = 0; i < board.getBoardSize(); i++){
            for (int j = 0; j < board.getBoardSize(); j++){
                if (board.getPieceAt(i,j) != 0 && board.getPieceAt(i, j) == player){
                    List<QueenMove> queenMoves = generateQueenMoves(board, i, j);
                    for (QueenMove queenMove : queenMoves){
                        List<ArrowShot> arrowMoves = generateArrowMoves(board, queenMove.getEndX(), queenMove.getEndY());
                        for (ArrowShot arrowShot : arrowMoves){
                            actions.add(new Action(queenMove, arrowShot));
                        }
                    
                    }
                }
            }
        }
        return actions;
    }

    private List<QueenMove> generateQueenMoves(Board board, int startX, int startY){
        List<QueenMove> queenMoves = new ArrayList<>();
        // move up
        for (int x = startX ; x < board.getBoardSize(); x++){
            if (!addQueenMoveIfValid(startX, startY, x, startY, board, queenMoves)){
                break;
            }
        }
        // move left
        for (int y = startY ; y > 0; y--){
            if (!addQueenMoveIfValid(startX, startY, startX, y, board, queenMoves)){
                break;
            }
        }
        // move right
        for (int y = startY ; y < board.getBoardSize(); y++){
            if (!addQueenMoveIfValid(startX, startY, startX, y, board, queenMoves)){
                break;
            }
        }
        // move down
        for (int x = startX ; x > 0; x--){
            if (!addQueenMoveIfValid(startX, startY, x, startY, board, queenMoves)){
                break;
            }
        }

        // move up-right

        // move up-left

        // move down-left

        // move down-right
        return queenMoves;
    }

    private List<ArrowShot> generateArrowMoves(Board board, int startX, int startY){
        List<ArrowShot> arrowMoves = new ArrayList<>();
        // arrow will have same logic as queen move 

        //shoot up
        for (int x = startX ; x < board.getBoardSize(); x++){
            if (!addArrowMoveIfValid(startX, startY, x, startY, board, arrowMoves)){
                break;
            }
        }

        // shoot down
        for (int x = startX ; x < 0; x--){
            if (!addArrowMoveIfValid(startX, startY, x, startY, board, arrowMoves)){
                break;
            }
        }

        // shoot right
        for (int y = startY ; y < board.getBoardSize(); y++){
            if (!addArrowMoveIfValid(startX, startY, startX, y, board, arrowMoves)){
                break;
            }
        }

        // shoot left
        for (int y = startY ; y < 0; y--){
            if (!addArrowMoveIfValid(startX, startY, startX, y, board, arrowMoves)){
                break;
            }
        }

        // shoot up-right

        // shoot up-left

        // shoot down-right

        // shoot down-left
        return arrowMoves;
    }

    private boolean addQueenMoveIfValid(int startX, int startY, int endX, int endY, Board board, List<QueenMove> queenMoves){
        if (board.getPieceAt(endX, endY) == 0){
            queenMoves.add(new QueenMove(startX, startY, endX, endY));
            return true;
        }
        return false;
    }

    private boolean addArrowMoveIfValid(int startX, int startY, int endX, int endY, Board board, List<ArrowShot> arrowMoves){
        if (board.getPieceAt(endX, endY) == 0){
            arrowMoves.add(new ArrowShot(startX, startY, endX, endY));
            return true;
        }
        return false;
    }
}
