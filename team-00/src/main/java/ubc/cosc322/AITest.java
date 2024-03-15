package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.nimbus.State;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;
import ygraph.ai.smartfox.games.amazons.HumanPlayer;

/**
 * An example illustrating how to implement a GamePlayer
 * @author Yong Gao (yong.gao@ubc.ca)
 * Jan 5, 2021
 *
 */
public class AITest extends GamePlayer{

    private GameClient gameClient = null; 
    private BaseGameGUI gamegui = null;
	
    private String userName = null;
    private String passwd = null;

    private TestPlayer playerStats = null;
    private Board board;
 
	
    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {				 
    	GamePlayer player;
        // player = new AITest(args[0], args[1]);
        player = new AITest("whatev", "loser");

        player.connect();
    	
    	if(player.getGameGUI() == null) {
    		player.Go();
    	}
    	else {
    		BaseGameGUI.sys_setup();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                	player.Go();
                }
            });
    	}
    }
	
    /**
     * Any name and passwd 
     * @param userName
      * @param passwd
     */
    public AITest(String userName, String passwd) {
    	this.userName = userName;
    	this.passwd = passwd;
    	
    	//To make a GUI-based player, create an instance of BaseGameGUI
    	//and implement the method getGameGUI() accordingly
    	this.gamegui = new BaseGameGUI(this);
    }
 


	@Override
	public void onLogin() {
		if (gameClient != null && gameClient.getUserName() != null) {
			userName = gameClient.getUserName();
			if (gamegui != null) {
				gamegui.setRoomInformation(gameClient.getRoomList());
			}
		} else {
			System.err.println("Error: Game client or user is not initialized.");
		}
	}

    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
        switch(messageType){
            case GameMessage.GAME_ACTION_START:

                break;
            case GameMessage.GAME_ACTION_MOVE:

                break;
            case GameMessage.GAME_STATE_JOIN:

                break;
            case GameMessage.GAME_STATE_BOARD:
                getGameGUI().setGameState((ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE));
                board = new Board((ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE));
                break;

        }
        return true;
    }
    
    
    @Override
    public String userName() {
    	return userName;
    }

	@Override
	public GameClient getGameClient() {
		// TODO Auto-generated method stub
		return this.gameClient;
	}

	@Override
	public BaseGameGUI getGameGUI() {
		// TODO Auto-generated method stub
		return  null;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
    	gameClient = new GameClient(userName, passwd, this);			
	}

 
}//end of class
