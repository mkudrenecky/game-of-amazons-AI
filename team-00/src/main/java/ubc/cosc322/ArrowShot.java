package ubc.cosc322;

public class ArrowShot {
    private byte startRow, startCol, endRow, endCol;

    public ArrowShot(byte startRow, byte startCol, byte endRow, byte endCol){
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
