package Server;

import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerStreamInput extends Thread {

    ObjectInputStream in;
    Game game;
    ServerPlayerHandler serverPlayerHandler;

    public PlayerStreamInput(ObjectInputStream in, Game game, ServerPlayerHandler serverPlayerHandler) {
        this.in = in;
        this.game = game;
        this.serverPlayerHandler = serverPlayerHandler;
    }
    @Override
    public void run() {
        waitForPlayerInput();
    }

    void waitForPlayerInput(){
        try {
            while(true) {
                PlayerStatePacket ReceivedPacket = (PlayerStatePacket) in.readObject();
                game.pullLine(ReceivedPacket.linePullForce, ReceivedPacket.gameState);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            serverPlayerHandler.disconnectPlayer();
        }
    }
}
