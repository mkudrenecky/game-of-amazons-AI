package ubc.cosc322;
import java.util.Map;
import java.util.ArrayList;
/*
 * The Board Class holds the state 
 */
public class Board extends State {

    public static final int BOARD_SIZE = 10;
    public static final int WHITE_QUEEN = 2;
    public static final int BLACK_QUEEN = 1;
    public static final int ARROW = 3;

    public int[][] board;

    public Board(ArrayList<Integer> gameState){
        this.board =  new int[BOARD_SIZE][BOARD_SIZE];
        int index=0;
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int value = gameState.get(index++);
                board[i][j] = value;
            }
        }

    }

    public void updateBoardState(ArrayList<Integer> gameState){
        int index = 0;
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int value = gameState.get(index++);
                board[i][j] = value;
            }
        }

    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public int getPieceAt(int i, int j) {
        return board[i][j];
    }

    public String boardToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j]).append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
