package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.nimbus.State;


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
        TestPlayer testPlayer1 = new TestPlayer("Jeff", false, 1, 1);
        TestPlayer testPlayer2 = new TestPlayer("Jennifer", true, 2,1);
        
        ArrayList<TestResult> testResult = new ArrayList<TestResult>();
        
        for(int i = 0; i < iterations; i++){
            testClass.startGame(testPlayer1, testPlayer2);
            
            testResult.add(new TestResult(testPlayer1, testPlayer2));
        }
        System.out.println(testClass.toString(testResult));
    
    }

    private void startGame(TestPlayer testPlayer1, TestPlayer testPlayer2){
        System.out.println("Test Game Start:");
        board = new Board(); //get new board
        makeMove(testPlayer1, testPlayer2);
    }

    private void makeMove(TestPlayer testPlayer1, TestPlayer testPlayer2){
        TestPlayer currentPlayer = (!testPlayer1.getIsBlack() ? testPlayer1 : testPlayer2);
        while(!gameWon){
            System.out.println("Making a move for " + currentPlayer.getPlayerName());
            System.out.println(board.boardToString());
            makeMinMaxMove(currentPlayer);
            checkWin(currentPlayer);
            currentPlayer = (testPlayer2 == currentPlayer ? testPlayer1 : testPlayer2);
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
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); j++){
                int tileChange = newBoard.getPieceAt(i, j) - oldBoard.getPieceAt(i, j);
                if((tileChange == 1 || tileChange == 2) && oldBoard.getPieceAt(i, j) == 0){
                    queenMove.setEndCol(i);
                    queenMove.setEndRow(j);
                }
                if((tileChange == 1 || tileChange == 2) && (oldBoard.getPieceAt(i, j) == 1 || oldBoard.getPieceAt(i, j) == 2)){
                    queenMove.setStartCol(i);
                    queenMove.setStartRow(j);
                    arrowShot.setEndCol(i);
                    arrowShot.setEndRow(j);
                }
                if(tileChange == 3){
                    arrowShot.setEndCol(i);
                    arrowShot.setEndRow(j);  
                }
                if(tileChange == -1 || tileChange == -2){
                    queenMove.setStartCol(i);
                    queenMove.setStartRow(j);
                }
            }
        }
        arrowShot.setStartCol(queenMove.getEndCol());
        arrowShot.setStartRow(queenMove.getEndRow());
        return new Action(queenMove, arrowShot);
    }

    public boolean checkIfMoveValid(Action action, int player, Board board){
        ArrayList<Action> legalMoves = ActionFactory.getActions(board, player);
        return legalMoves.contains(action);
    }

}
