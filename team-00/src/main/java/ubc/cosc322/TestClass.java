package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.nimbus.State;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;
import ygraph.ai.smartfox.games.amazons.HumanPlayer;

public class TestClass{
    
    private static int iterations = 5;
    private static TestPlayer testPlayer1;
    private static TestPlayer testPlayer2;
    private Action action;
    private Board board;
    private static ArrayList<TestResult> testResult;
    private int gameWon;


    public static void main(String[] args) {
        testPlayer1 = new TestPlayer("Jeff", true, 1);
        testPlayer2 = new TestPlayer("Jennifer", false, 2);
        
        testResult = new ArrayList<TestResult>();
        
        for(int i = 0; i < iterations; i++){
            int gameWinner = startGame();

    
            testResult.add(new TestResult(testPlayer1.getHeuristicName(), testPlayer2.getHeuristicName(), gameWinner));

        }
    
    }

    private static int startGame(){
        System.out.println("Test Game Start:");
        // board = new Board(); //get new board
        nextTurn();

        return -1;

    }

    private void makeMove(){
        while(!gameWon){
            makeMinMaxMove()
        }
    } 


    private void makeMinMaxMove(TestPlayer player){
        int depth = 2;
        Action bestAction = MinMax.findBestAction(board, depth, player.getIsBlack(), player.getHeuristicType());
        board.updateBoardState(bestAction, board);
    }




}
