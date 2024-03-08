package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

public class Action {
    private QueenMove queenMove;
    private ArrowShot arrowShot;
    private int player;
   
    public Action(QueenMove queenMove, ArrowShot arrowShot) {
        this.queenMove = queenMove;
        this.arrowShot = arrowShot;
    }

    public Action(Map<String, Object> serverResponse) {
        System.out.println("Receiving move from server...");
    
        List<Integer> queenCurrPos = (List<Integer>) serverResponse.get(AmazonsGameMessage.QUEEN_POS_CURR);
        List<Integer> queenNextPos = (List<Integer>) serverResponse.get(AmazonsGameMessage.QUEEN_POS_NEXT);
        List<Integer> arrowPos = (List<Integer>) serverResponse.get(AmazonsGameMessage.ARROW_POS);
    
        // Create QueenMove object
        QueenMove queenMove = new QueenMove(
            queenCurrPos.get(0) - 1, 
            queenCurrPos.get(1) - 1, 
            queenNextPos.get(0) - 1, 
            queenNextPos.get(1) - 1
        );
    
        // Create ArrowShot object
        ArrowShot arrowShot = new ArrowShot(
            arrowPos.get(0) - 1, 
            arrowPos.get(1) - 1
        );
        this.queenMove = queenMove;
        this.arrowShot = arrowShot;
    }

    public QueenMove getQueenMove() {
        return queenMove;
    }

    public ArrowShot getArrowShot() {
        return arrowShot;
    }

    public Map<String, Object> toServerResponse() {
        System.out.println("sending move to server...");
        System.out.println("Queen current position: " + (queenMove.getStartRow() + 1) + ", " + (queenMove.getStartCol() + 1));
        System.out.println("Queen next position: " + (queenMove.getEndRow() + 1) + ", " + (queenMove.getEndCol() + 1));
        System.out.println("Arrow position: " + (arrowShot.getEndRow() + 1) + ", " + (arrowShot.getEndCol() + 1));
        Map<String, Object> map = new HashMap<>();
        map.put(AmazonsGameMessage.QUEEN_POS_CURR, new ArrayList<>(Arrays.asList(queenMove.getStartRow()+1,queenMove.getStartCol()+1)));
        map.put(AmazonsGameMessage.QUEEN_POS_NEXT, new ArrayList<>(Arrays.asList(queenMove.getEndRow()+1,queenMove.getEndCol()+1)));
        map.put(AmazonsGameMessage.ARROW_POS, new ArrayList<>(Arrays.asList(arrowShot.getEndRow()+1, arrowShot.getEndCol()+1)));
        return map;
    }


}
