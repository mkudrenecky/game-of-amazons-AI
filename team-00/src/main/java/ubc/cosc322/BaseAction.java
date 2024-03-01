package ubc.cosc322;

public abstract class BaseAction<T extends State>{
    private double actionCost;
    private String actionDescription;

    public BaseAction(String actionDescription, double actionCost){
        this.actionDescription = actionDescription;
        this.actionCost = actionCost;
    }
    public BaseAction(String actionDescription){
        this.actionDescription = actionDescription;
    }

    //must be implemented by subclass
    //returns state after performing the action
    public abstract T performAction();

    public double actionCost(){
        return this.actionCost;
    }
    public String toString(){
        return this.actionDescription;
    }
}
