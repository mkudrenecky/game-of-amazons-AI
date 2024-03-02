package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

public class Action {
    private QueenMove queenMove;
    private ArrowShot arrowShot;
   
    public Action(QueenMove queenMove, ArrowShot arrowShot) {
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
        Map<String, Object> map = new HashMap<>();
        map.put(AmazonsGameMessage.QUEEN_POS_CURR, new ArrayList<>(Arrays.asList(queenMove.getStartCol()+1,queenMove.getStartRow()+1)));
        map.put(AmazonsGameMessage.QUEEN_POS_NEXT, new ArrayList<>(Arrays.asList(queenMove.getEndCol()+1,queenMove.getEndRow()+1)));
        map.put(AmazonsGameMessage.ARROW_POS, new ArrayList<>(Arrays.asList(arrowShot.getEndY()+1, arrowShot.getEndX()+1)));
        return map;
    }

}
