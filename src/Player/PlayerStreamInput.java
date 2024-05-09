package Player;

import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerStreamInput extends Thread {

    ObjectInputStream in;
    Player player;

    public PlayerStreamInput(ObjectInputStream in, Player player) {
        this.in = in;
        this.player = player;
    }
    @Override
    public void run() {
        waitForServerInput();
    }

    void waitForServerInput(){
        try {
            while(true) {
                GameStatePacket ReceivedPacket = (GameStatePacket) in.readObject();
                player.updatePlayerData(ReceivedPacket);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            player.disconnectFromServer();
        }
    }
}

