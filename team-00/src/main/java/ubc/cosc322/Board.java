package ubc.cosc322;
import java.util.Map;
import java.util.ArrayList;
/*
 * The Board Class holds the state 
 */
public class Board {

    public static final byte BOARD_SIZE = 10;
    public static final byte WHITE_QUEEN = 2;
    public static final byte BLACK_QUEEN = 1;
    public static final byte ARROW = 3;

    public byte[][] board;

    public Board(ArrayList<Byte> gameState){
        this.board =  new byte[BOARD_SIZE][BOARD_SIZE];
        byte index=12;
        for (byte i = 0; i < BOARD_SIZE; i++){
            for (byte j = 0; j < BOARD_SIZE; j++){
                byte value = gameState.get(index++);
                board[i][j] = value;
            }
            index++;
        }
    }
    public Board(ArrayList<Integer> gameState, boolean hey){
        this.board =  new byte[BOARD_SIZE][BOARD_SIZE];
        byte index=12;
        for (byte i = 0; i < BOARD_SIZE; i++){
            for (byte j = 0; j < BOARD_SIZE; j++){
                int value =  gameState.get(index++);
                board[i][j] = (byte) value;
            }
            index++;
        }
    }

    public Board(Board oldBoard) {
        this.board = new byte[BOARD_SIZE][BOARD_SIZE];
        for (byte i = 0; i < BOARD_SIZE; i++) {
            for (byte j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = (byte) oldBoard.getPieceAt(i, j);
            }
        }
    }

    public Board(Board oldBoard, Action action){
        this.board = new byte[BOARD_SIZE][BOARD_SIZE];
        Board newBoard = new Board(oldBoard);
        newBoard.updateBoardState(action, newBoard);
        for (byte i = 0; i < BOARD_SIZE; i++){
            for (byte j = 0; j < BOARD_SIZE; j++){
                board[i][j] = (byte) newBoard.getPieceAt(i, j);
            }
        }
    }

    public void updateBoardState(Action action, Board board){
        QueenMove move = action.getQueenMove();
        ArrowShot arrow = action.getArrowShot();

        // get the color/player that made the move
        byte player = (byte) board.getPieceAt(move.getStartRow(),move.getStartCol());
        // System.out.println("PLayer moving: " + player);
        
        // update board to 0 where queen was and move queen
        board.setPieceAt(move.getStartRow(), move.getStartCol(), (byte)0);
        board.setPieceAt(move.getEndRow(), move.getEndCol(), player);
      

        // update arrow on board
        board.setPieceAt(arrow.getEndRow(), arrow.getEndCol(), ARROW);

    }

    public byte getBoardSize(){
        return BOARD_SIZE;
    }

    public byte getPieceAt(byte i, byte j) {
        return board[i][j];
    }

    public void setPieceAt(byte i, byte j, byte value) {
        board[i][j] = value;
    }

    public String boardToString(){
        StringBuilder sb = new StringBuilder();
        for (byte i = 0; i < BOARD_SIZE; i++) {
            for (byte j = 0; j < BOARD_SIZE; j++) {
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
