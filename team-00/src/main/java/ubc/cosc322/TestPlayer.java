package ubc.cosc322;

public class TestPlayer {
    private String playerName;
    private boolean isBlack;
    private String heuristicName;
    private int heuristicType;

    public TestPlayer(String playerName, boolean isBlack, int heuristicType){
        this.playerName = playerName;
        this.isBlack = isBlack;
        this.heuristicType = heuristicType;
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


    public String toString(){
        return "Player type: " + heuristicName + " Colour: " + (isBlack ? "Black" : "White"); 
    }
}
