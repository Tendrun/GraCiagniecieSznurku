package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerStreamOutput extends Thread {

    ObjectOutputStream out;
    Game game;

    public ServerStreamOutput(ObjectOutputStream out, Game game){
        this.game = game;
        this.out = out;
    }
    @Override
    public void run() {
        sendToPlayerChanges();
    }

    void sendToPlayerChanges() {
        while(true) {
            try {
                GameStatePacket gameSendPacket = game.getGameState();
                out.writeObject(gameSendPacket);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
