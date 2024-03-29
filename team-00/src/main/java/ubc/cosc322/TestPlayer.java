package ubc.cosc322;

public class TestPlayer {
    private String playerName;
    private boolean isBlack;
    private String heuristicName;
    private int heuristicType;
    private int depth;
    private int nodesExplored;
    private boolean winner;
    private long timeLimit;

    public TestPlayer(String playerName, boolean isBlack, int heuristicType, int depth, long timeLimit){
        this.playerName = playerName;
        this.isBlack = isBlack;
        this.heuristicType = heuristicType;
        this.depth = depth;
        this.timeLimit = timeLimit;
        setHeuristicName();
    }
    
    private void setHeuristicName(){
        switch(this.heuristicType){
            case 0:
                this.heuristicName = "Random Heuristic";
                break;
            case 1:
                this.heuristicName = "Mobility Heuristic";
                break;
            case 2:
                this.heuristicName = "Territory Heuristic";
                break;
            case 3:
                this.heuristicName = "Mobility-Territory Heuristic";
                break;
        }
    }
    
    public String getPlayerName(){
        return this.playerName;
    }
    public boolean getIsBlack(){
        return this.isBlack;
    }
    public String getHeuristicName(){
        return this.heuristicName;
    }
    public int getHeuristicType(){
        return this.heuristicType;
    }
    public int getDepth(){
        return this.depth;
    }
    public boolean getWinner(){
        return this.winner;
    }
    public void setWinner(boolean winner){
        this.winner = winner;
    }
    public void incrementNodes(){
        nodesExplored++;
    }
    public long getTimeLimit(){
        return this.timeLimit;
    }

    public String toString(){
        return "Player Name: " + playerName + " Winner?: " + winner + ", Player type: " + heuristicName + ", Colour: " + (isBlack ? "Black" : "White") + ", Depth: " + depth; 
    }
}
