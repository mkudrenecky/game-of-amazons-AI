package ubc.cosc322;

public class Arrow {
    private byte row, col;

    public Arrow (byte row, byte col){
        this.row = row;
        this.col = col;
    }

    public byte getRow() {
        return row;
    }

    public byte getCol() {
        return col;
    }
}
