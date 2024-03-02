package ubc.cosc322;

public class Queen {
    private Player player;
    private int x;
    private int y;

    public Queen(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

