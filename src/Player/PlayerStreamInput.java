package Player;

import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.InvalidClassException;
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
        } catch (InvalidClassException e){
            System.err.println("InvalidClassException :(((");
        } catch (ClassNotFoundException e) {
            System.err.println("PAKIETTTT");
        } catch (IOException e) {
            player.disconnectFromServer();
        }
    }
}

