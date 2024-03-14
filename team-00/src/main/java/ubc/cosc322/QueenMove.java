package ubc.cosc322;

public class QueenMove {
    private int startRow, startCol, endRow, endCol;

    public QueenMove(){}
    public QueenMove(int startRow, int startCol, int endRow, int endCol){
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

    public boolean equals(QueenMove queenMove){
        return (this.getEndCol() == queenMove.getEndCol() && this.getEndRow() == queenMove.getEndRow() && this.getStartCol() == queenMove.getStartCol() && this.getStartRow() == queenMove.getStartRow());
    }
}
