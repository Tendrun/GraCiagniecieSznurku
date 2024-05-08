package Player;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
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
        while(true){
            sendToServerChanges();
            try {
                Thread.sleep(msDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void sendToServerChanges() {
        try {
            out.writeObject(player.getPlayerStatePacket());
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
