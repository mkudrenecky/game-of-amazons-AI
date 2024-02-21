package ubc.cosc322;
import java.util.Map;
import java.util.ArrayList;
/*
 * The Board Class holds the state 
 */
public class Board {

    private final int BOARD_SIZE = 10;
    private final int WHITE_QUEEN = 2;
    private final int BLACK_QUEEN = 1;
    private final int ARROW = 3;

    public Board(ArrayList<Integer> gameState){
        int [][] board = new int [BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int index = j * BOARD_SIZE + i;
                if (gameState.get(index) == WHITE_QUEEN){

                }
            }
        }

    }

    public void updateBoardState(Map<String, Object> state){

    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public Queen getQueenAt(int i, int j){
        return queens[i][j];
    }

    public Arrow getArrowAt(int i, int j){
        return arrows[i][j];
    }

    
}
