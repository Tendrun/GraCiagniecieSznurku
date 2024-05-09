package Player;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;

public class PlayerStreamOutput extends Thread {

    ObjectOutputStream out;
    Player player;
    int msDelay;

    public PlayerStreamOutput(ObjectOutputStream out, Player player, int msDelay){
        this.player = player;
        this.out = out;
        this.msDelay = msDelay;
    }
    @Override
    public void run() {
        sendToServerChanges();
    }

    void sendToServerChanges() {
        try {
            while(true){
                out.writeObject(player.getPlayerStatePacket());
                out.flush();
                Thread.sleep(msDelay);
            }
        } catch (InvalidClassException e){
            System.err.println("InvalidClassException :(((");
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            player.disconnectFromServer();
        }
    }
}
