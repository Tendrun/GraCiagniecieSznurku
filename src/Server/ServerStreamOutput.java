package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;

public class ServerStreamOutput  {
    ObjectOutputStream out;
    Game game;
    ServerPlayerHandler serverPlayerHandler;
    public ServerStreamOutput(ObjectOutputStream out, Game game, ServerPlayerHandler serverPlayerHandler){
        this.game = game;
        this.out = out;
        this.serverPlayerHandler = serverPlayerHandler;
    }
    public synchronized void sendToPlayerChanges() {
            try {
                GameStatePacket gameSendPacket = game.getGameState();
                out.writeObject(gameSendPacket);
                out.flush();
            } catch (InvalidClassException e){
                System.err.println("InvalidClassException :(((");
            } catch (IOException e) {
                serverPlayerHandler.disconnectPlayer();
            }
    }
}
