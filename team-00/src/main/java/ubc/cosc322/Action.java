package ubc.cosc322;

public class Action extends BaseAction<State>{
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

    @Override
    public State performAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performAction'");
    }


}
