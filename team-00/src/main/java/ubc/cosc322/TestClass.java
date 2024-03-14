package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.nimbus.State;
import javax.swing.plaf.synth.SynthSeparatorUI;

import ygraph.ai.smartfox.games.BaseGameGUI;


public class TestClass{
    
    private static int iterations = 5;
    // private TestPlayer testPlayer1;
    // private TestPlayer testPlayer2;
    private Action action;
    private Board board;
    // private ArrayList<TestResult> testResult;
    private boolean gameWon;




    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        TestPlayer testPlayer1 = new TestPlayer("Jeff", false, 2, 1);
        TestPlayer testPlayer2 = new TestPlayer("Jennifer", true, 1,1);
        
        ArrayList<TestResult> testResult = new ArrayList<TestResult>();
        
        for(int i = 0; i < iterations; i++){
            testClass.startGame(testPlayer1, testPlayer2);
            
            testResult.add(new TestResult(testPlayer1, testPlayer2));
        }
        System.out.println(testClass.toString(testResult));
    
    }
    
    //empty constructor
    public TestClass(){

    }

    private void startGame(TestPlayer testPlayer1, TestPlayer testPlayer2){
        board = new Board(); //get new board
        makeMove(testPlayer1, testPlayer2);
    }

    private void testActionFactory(){

    }

    private void makeMove(TestPlayer testPlayer1, TestPlayer testPlayer2){
        TestPlayer currentPlayer = (!testPlayer1.getIsBlack() ? testPlayer1 : testPlayer2);
        Board oldBoard = null;
        while(!gameWon){
            System.out.println("Making a move for " + currentPlayer.getPlayerName() + " " + (currentPlayer.getIsBlack()? 1 : 2 ));
            // System.out.println(board.boardToString());
            oldBoard = new Board(this.board);
            makeMinMaxMove(currentPlayer);
            checkWin(currentPlayer);
            // System.out.println(findMove(oldBoard, board).toString());
            currentPlayer = (testPlayer2 == currentPlayer ? testPlayer1 : testPlayer2);
            System.out.println("Legal move? " + checkIfMoveValid(findMove(oldBoard, this.board), (currentPlayer.getIsBlack() ? 2 : 1), oldBoard));

            checkWin(currentPlayer);
        }
    } 

    private void checkWin(TestPlayer player){
        if(MinMax.findBestAction(board, player.getDepth(), player.getIsBlack() ? 1 : 2, player.getHeuristicType()) == null){
            gameWon = true;
            player.setWinner(true);
        }
    }

    private void makeMinMaxMove(TestPlayer player){
        Action bestAction = MinMax.findBestAction(board, player.getDepth(), player.getIsBlack() ? 1 : 2, player.getHeuristicType());
        board.updateBoardState(bestAction, board);
    }

    public String toString(ArrayList<TestResult> testResults){
        String result = "";
        int player1Wins = 0;
        int player2Wins = 0;
        int count = 0;
        System.out.println(testResults.get(0).getTestPlayer1().toString());
        System.out.println(testResults.get(0).getTestPlayer2().toString());

        for(TestResult testResult : testResults){
            count++;
            result = result + "\nGame " + count + ":\n";
            result = result + "___________________________________\n";
            if(testResult.getTestPlayer1().getWinner())
                player1Wins++;
            if(testResult.getTestPlayer2().getWinner())
                player2Wins++;
        }
        result = result + "___________________________________\n";
        result = result + "Wins\n";

        result = result + "Player1: " + player1Wins + "\n";
        result = result + "Player2: " + player2Wins + "\n";
        //add concat to string with results summary
        return result;
    }

    public Action findMove(Board oldBoard, Board newBoard){
        QueenMove queenMove = new QueenMove();
        ArrowShot arrowShot = new ArrowShot();
        //row = i, col = j
        for(int row = 0; row < board.getBoardSize(); row++){
            for(int col = 0; col < board.getBoardSize(); col++){
                int tileChange = newBoard.getPieceAt(row, col) - oldBoard.getPieceAt(row, col);
                //case queen end on empty pos
                if((tileChange == 1 || tileChange == 2) && oldBoard.getPieceAt(row, col) == 0){
                    queenMove.setEndCol(col);
                    queenMove.setEndRow(row);
                }
                //case queen leave and shoot arrow on start pos
                if((tileChange == 1 || tileChange == 2) && (oldBoard.getPieceAt(row, col) == 1 || oldBoard.getPieceAt(row, col) == 2)){
                    queenMove.setStartCol(col);
                    queenMove.setStartRow(row);
                    arrowShot.setEndCol(col);
                    arrowShot.setEndRow(row);
                }
                //case arrow end pos
                if(tileChange == 3){ 
                    arrowShot.setEndCol(col);
                    arrowShot.setEndRow(row);  
                }
                //case queen leave
                if((tileChange == -1 && oldBoard.getPieceAt(row, col) ==1) || (tileChange == -2 && oldBoard.getPieceAt(row, col) ==2)){
                    queenMove.setStartCol(col);
                    queenMove.setStartRow(row);
                }
            }
        }
        arrowShot.setStartCol(queenMove.getEndCol());
        arrowShot.setStartRow(queenMove.getEndRow());
        return new Action(queenMove, arrowShot);
    }

    public boolean checkIfMoveValid(Action action, int player, Board board){
        // System.out.println("Checking if move valid for " + player);
        ArrayList<Action> legalMoves = ActionFactory.getActions(board, player);
        boolean legal = false;
        for(Action legalMove : legalMoves){
            legal = (legalMove.equals(action));
            if(legal)
                break;
        }
        return legal;
    }

}
