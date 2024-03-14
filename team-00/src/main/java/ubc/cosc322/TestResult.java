package ubc.cosc322;

import java.io.ByteArrayInputStream;

public class TestResult {
    private TestPlayer testPlayer1;
    private TestPlayer testPlayer2;

    
    public TestResult(TestPlayer testPlayer1, TestPlayer testPlayer2){
        this.testPlayer1 = testPlayer1;
        this.testPlayer2 = testPlayer2;

    }

    public TestPlayer getTestPlayer1(){
        return testPlayer1;
    }
    public TestPlayer getTestPlayer2(){
        return testPlayer2;
    }
    public String toString(){
        return testPlayer1.toString() + "\n" + testPlayer2.toString();
    }
}
