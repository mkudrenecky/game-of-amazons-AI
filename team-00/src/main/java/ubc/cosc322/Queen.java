package ubc.cosc322;

public class Queen {
    private Player player;
    private byte x;
    private byte y;

    public Queen(Player player, byte x, byte y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}

