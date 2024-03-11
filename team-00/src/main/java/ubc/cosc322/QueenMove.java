package ubc.cosc322;

public class QueenMove {
    private byte startRow, startCol, endRow, endCol;

    public QueenMove(byte startRow, byte startCol, byte endRow, byte endCol){
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    public byte getStartRow() {
        return startRow;
    }

    public byte getStartCol() {
        return startCol;
    }

    public byte getEndRow() {
        return endRow;
    }

    public byte getEndCol() {
        return endCol;
    }
}
