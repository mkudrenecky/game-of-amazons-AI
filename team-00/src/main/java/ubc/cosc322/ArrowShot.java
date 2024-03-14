package ubc.cosc322;

public class ArrowShot {
    private int startRow, startCol, endRow, endCol;

    public ArrowShot(){}
    public ArrowShot(int startRow, int startCol, int endRow, int endCol){
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setStartCol(int x){
        this.startCol = x;
    }

    public void setEndCol(int x){
        this.endCol = x;
    }

    public void setStartRow(int x){
        this.startRow = x;
    }

    public void setEndRow(int x){
        this.endRow = x;
    }
}
