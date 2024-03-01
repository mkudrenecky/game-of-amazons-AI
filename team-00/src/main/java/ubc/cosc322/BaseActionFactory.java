package ubc.cosc322;

public class BaseActionFactory<T extends State> {
    //subclasses must overwrite this method
    public Action<T>[] getActions(T state){
        return null;
    }
}
