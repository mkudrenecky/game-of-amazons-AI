package ubc.cosc322;

import java.io.ByteArrayInputStream;

public class TestResult {
    private String player1Type;
    private String player2Type;
    private byte winner;
    
    public TestResult(String player1Type, String player2Type, byte winner){
        this.player1Type = player1Type;
        this.player2Type = player2Type;
        this.winner = winner;
    }
}
