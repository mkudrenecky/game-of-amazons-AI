package ubc.cosc322;

import java.util.Comparator;

public class sortByEval implements Comparator<Action>{

    @Override
    public int compare(Action a, Action b) {
        return b.value - a.value; 
    }
    
}
