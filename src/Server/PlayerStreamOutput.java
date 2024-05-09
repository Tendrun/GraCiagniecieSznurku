package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerStreamOutput extends Thread {

    ObjectOutputStream out;
    Game game;
    ServerPlayerHandler serverPlayerHandler;
    public PlayerStreamOutput(ObjectOutputStream out, Game game, ServerPlayerHandler serverPlayerHandler){
        this.game = game;
        this.out = out;
        this.serverPlayerHandler = serverPlayerHandler;
    }
    @Override
    public void run() {
        sendToPlayerChanges();
    }

    void sendToPlayerChanges() {
        try {
            while (true){
                GameStatePacket gameSendPacket = game.getGameState();
                out.writeObject(gameSendPacket);
                out.flush();
            }
        } catch (IOException e) {
            serverPlayerHandler.disconnectPlayer();
        }
    }
}
