package ubc.cosc322;
import java.util.Map;
import java.util.ArrayList;
/*
 * The Board Class holds the state 
 */
public class Board {

    public static final int BOARD_SIZE = 10;
    public static final int WHITE_QUEEN = 2;
    public static final int BLACK_QUEEN = 1;
    public static final int ARROW = 3;

    public int[][] board;


    public Board(){
        this.board = new int[][]{
            { 0, 0, 0, WHITE_QUEEN, 0, 0, WHITE_QUEEN, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 },
                    { WHITE_QUEEN, 0, 0, 0, 0, 0, 0, 0, 0, WHITE_QUEEN },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 },
                    { BLACK_QUEEN, 0, 0, 0, 0, 0, 0, 0, 0, BLACK_QUEEN },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 },
                    { 0, 0, 0, BLACK_QUEEN, 0, 0, BLACK_QUEEN, 0, 0, 0 } };
    }

    public Board(ArrayList<Integer> gameState){
        this.board =  new int[BOARD_SIZE][BOARD_SIZE];
        int index=12;
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int value = gameState.get(index++);
                this.board[i][j] = value;
            }
            index++;
        }

    }

    public Board(Board oldBoard) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = oldBoard.getPieceAt(i, j);
            }
        }
    }

    public Board(Board oldBoard, Action action){
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        Board newBoard = new Board(oldBoard);
        newBoard.updateBoardState(action);
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                this.board[i][j] = newBoard.getPieceAt(i, j);
            }
        }
    }

    public void updateBoardState(Action action){
        QueenMove move = action.getQueenMove();
        ArrowShot arrow = action.getArrowShot();

        // get the color/player that made the move
        int player = this.getPieceAt(move.getStartRow(),move.getStartCol());
        // System.out.println("PLayer moving: " + player);
        
        // update board to 0 where queen was and move queen
        this.setPieceAt(move.getStartRow(), move.getStartCol(), 0);
        this.setPieceAt(move.getEndRow(), move.getEndCol(), player);
      

        // update arrow on board
        this.setPieceAt(arrow.getEndRow(), arrow.getEndCol(), ARROW);

    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public int getPieceAt(int i, int j) {
        return board[i][j];
    }

    public void setPieceAt(int i, int j, int value) {
        board[i][j] = value;
    }

    public String boardToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isGameOver(){
       return true;
    }
    
}
