package ubc.cosc322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

public class Action {
    private QueenMove queenMove;
    private ArrowShot arrowShot;
    private byte player;
   
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
        map.put(AmazonsGameMessage.QUEEN_POS_CURR, new ArrayList<>(Arrays.asList(queenMove.getStartRow()+1,queenMove.getStartCol()+1)));
        map.put(AmazonsGameMessage.QUEEN_POS_NEXT, new ArrayList<>(Arrays.asList(queenMove.getEndRow()+1,queenMove.getEndCol()+1)));
        map.put(AmazonsGameMessage.ARROW_POS, new ArrayList<>(Arrays.asList(arrowShot.getEndRow()+1, arrowShot.getEndCol()+1)));
        return map;
    }

}
