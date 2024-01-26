package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.BaseGameGUI;
import ygraph.ai.smartfox.games.GameClient;
import ygraph.ai.smartfox.games.GameMessage;
import ygraph.ai.smartfox.games.GamePlayer;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

public class COSC322Test extends GamePlayer {

    private GameClient gameClient = null;
    private BaseGameGUI gamegui = null;

    private String userName = null;
    private String passwd = null;

    public static void main(String[] args) {
        COSC322Test player = new COSC322Test(args[0], args[1]);

        if (player.getGameGUI() == null) {
            player.Go();
        } else {
            BaseGameGUI.sys_setup();
            java.awt.EventQueue.invokeLater(() -> player.Go());
        }
    }

    public COSC322Test(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
        this.gamegui = new BaseGameGUI(this);
    }

    @Override
    public void onLogin() {
        userName = gameClient.getUserName();
        if (gamegui != null) {
            gamegui.setRoomInformation(gameClient.getRoomList());
        }
    }

    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
        System.out.println("Message Type: " + messageType);
        System.out.println("Message Details: " + msgDetails);

        if (GameMessage.GAME_STATE_BOARD.equals(messageType)) {
            if (msgDetails.containsKey(AmazonsGameMessage.GAME_STATE)) {
                @SuppressWarnings("unchecked")
				ArrayList<Integer> gameState = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);
                System.out.println("Game State Received: " + gameState);
                getGameGUI().setGameState(gameState);
            } else {
                System.err.println("Game state key '" + AmazonsGameMessage.GAME_STATE + "' not found in message details.");
            }
        } else if (GameMessage.GAME_ACTION_MOVE.equals(messageType)) {
            getGameGUI().updateGameState(msgDetails);
        }
        return true;
    }

    @Override
    public String userName() {
        return userName;
    }

    @Override
    public GameClient getGameClient() {
        return this.gameClient;
    }

    @Override
    public BaseGameGUI getGameGUI() {
        return this.gamegui;
    }

    @Override
    public void connect() {
        gameClient = new GameClient(userName, passwd, this);
    }
}
