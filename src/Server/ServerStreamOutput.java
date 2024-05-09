package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerStreamOutput extends Thread {

    ObjectOutputStream out;
    Game game;
    ServerPlayerHandler serverPlayerHandler;
    public ServerStreamOutput(ObjectOutputStream out, Game game, ServerPlayerHandler serverPlayerHandler){
        this.game = game;
        this.out = out;
        this.serverPlayerHandler = serverPlayerHandler;
    }
    @Override
    public void run() {
        //sendToPlayerChanges();
    }

    public void sendToPlayerChanges() {
            try {
                GameStatePacket gameSendPacket = game.getGameState();
                out. writeObject(gameSendPacket);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
                //serverPlayerHandler.disconnectPlayer();
            }
    }
}
