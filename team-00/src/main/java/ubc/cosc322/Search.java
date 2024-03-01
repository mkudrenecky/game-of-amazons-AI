package ubc.cosc322;

import java.util.ArrayList;

public abstract class Search<T extends State> {
    
    public Search(ArrayList<Integer> gameState){

    }
    //compute heuristic value
    public double compute_h_value(){
        return 0;
    }

    //expand to the chosen node
    public void expand(){

    }

    //return an array of possible Actions
    public Action<T>[] findPossibleActions(T state){
        return null;
    }
    //test whether current state is the goal
    abstract boolean goalTest();

    //create a new node
    public void makeNode(){

    }
    //set f_value, computed using h_value
    public void set_f_value(){

    }
    public void setGoalState(){

    }

}
